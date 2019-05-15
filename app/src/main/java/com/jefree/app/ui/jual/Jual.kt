package com.jefree.app.ui.jual

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import com.jefree.app.R
import com.jefree.app.common.Constant
import com.jefree.app.common.toast
import com.jefree.app.model.Profile.Data
import com.jefree.app.model.Profile.Profile
import com.jefree.app.ui.home.activity.EditProfile
import com.jefree.app.ui.konfirmasipenjualan.KonfirmasiPenjualan
import kotlinx.android.synthetic.main.activity_jual.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.IOException
import kotlin.math.round


class Jual : AppCompatActivity(), JualView {
    private val presenter = JualPresenter(this)
    private var statu = 0
    private var locationManager: LocationManager? = null
    private var mlat = ""
    private var mlong = ""
    private var distance = ""
    private lateinit var data: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jual)
        setupView()
    }

    private fun setupView() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Jual"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter.showData()

        btnJualLanjut.setOnClickListener {
            if (statu != 0) {
                if (etMinyak.text.toString() != "") {
                    if (mlat != "" && mlong != "") {
                        val loc = Location("")
                        loc.latitude = -7.7795975
                        loc.longitude = 110.3866014

                        val loc2 = Location("")
                        loc2.latitude = mlat.toDouble()
                        loc2.longitude = mlong.toDouble()

                        val distanceInMeters = loc.distanceTo(loc2) / 1000
                        val map = mutableMapOf<String, String>()

                        distance = round(distanceInMeters).toString()

                        map["quantity"] = etMinyak.text.toString()
                        map["distance"] = distance

                        presenter.sale(map)
                    } else {
                        this.toast("Masukan alamat dengan benar")
                    }
                } else {
                    this.toast("Masukan jumlah minyakmu")
                }
            } else {
                this.toast("Isi alamatmu terlebih dahulu")
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onError(message: String) {
        this.toast(message)
    }

    override fun onSuccess(data: Data, status: Int) {
        statu = status
        tvJualName.text = data.profile?.name
        tvJualPhone.text = data.profile?.phone

        if (status == 1) {
            tvJualAddress.text = data.profile?.address
            val loc = getLocationFromAddress(this, data.profile?.address!!)
            if (loc != null) {
                mlat = loc.latitude.toString()
                mlong = loc.longitude.toString()
            }
        } else {
            tvJualAddress.text = resources.getString(R.string.edit_alamat_pada_profil_kamu_untuk_menambahkan_alamat)
            tvJualAddress.setBackgroundResource(R.drawable.rounded_linear_layout_border_gray)
        }

        if (data.profile?.address == "" || data.profile?.address == null) {
            tvJualAddress.text = resources.getString(R.string.edit_alamat_pada_profil_kamu_untuk_menambahkan_alamat)
            tvJualAddress.setBackgroundResource(R.drawable.rounded_linear_layout_border_gray)
        }

        tvJualAddress.setOnClickListener {
            show_data(data)

        }
    }

    private fun show_data(data: Data?) {
        run {
            this.data = data!!
            namaUser?.text = data.profile?.name
            phone?.text = data.profile?.phone
            email?.text = data.profile?.email
            val intent = Intent(this, EditProfile::class.java)
            intent.putExtra(Constant.USERS, data)
            startActivity(intent)
            finish()
        }
    }

    private fun getLocationFromAddress(context: Context, strAddress: String): LatLng? {

        val coder = Geocoder(context)
        val address: List<Address>?
        var p1: LatLng? = null
        val location: Address?

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5)
            if (address.isEmpty() || address == null) {
                return null
            }

            location = address[0]

            println("alamat1" + location)

            p1 = LatLng(location.latitude, location.longitude)

            println("alamat" + address[0])
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return p1
    }

    private fun jual(price: String, distance: String) {
        val intent = Intent(this, KonfirmasiPenjualan::class.java)
        intent.putExtra("phone", tvJualPhone.text.toString())
        intent.putExtra("name", tvJualName.text.toString())
        intent.putExtra("address", tvJualAddress.text.toString())
        intent.putExtra("minyak", etMinyak.text.toString())
        intent.putExtra("price", price)
        intent.putExtra("distance", distance)
        startActivity(intent)
        finish()
    }

    override fun onSale(price: String) {
        jual(price, distance)
    }


}
