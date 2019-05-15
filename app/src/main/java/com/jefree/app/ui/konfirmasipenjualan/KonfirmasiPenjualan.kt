package com.jefree.app.ui.konfirmasipenjualan

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jefree.app.R
import com.jefree.app.ui.jual.Jual
import kotlinx.android.synthetic.main.activity_konfirmasi_penjualan.*
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import com.jefree.app.JefreeApp
import com.jefree.app.common.toast

class KonfirmasiPenjualan : AppCompatActivity(), KonfirmasiView {

    val presenter = KonfirmasiPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_penjualan)

        setUpView()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpView() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Jual"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ivEditKonfirmasi.setOnClickListener {
            startActivity(Intent(this, Jual::class.java))
            finish()
        }

        name.text             = intent.getStringExtra("name")
        phone.text            = intent.getStringExtra("phone")
        address.text          = intent.getStringExtra("address")
        minyak.text           = "${intent.getStringExtra("minyak")} Kg"
        tvHasilPenjualan.text = "Hasil Penjualan : Rp "+intent.getStringExtra("price")

        btnJualKonfirmasi.setOnClickListener {
            if(intent.getStringExtra("price").toInt() > 0 ){
                val map = mutableMapOf<String, String>()
                map["user_id"]       = JefreeApp.sp.id.toString()
                map["quantity"]      = intent.getStringExtra("minyak")
                map["price"]         = intent.getStringExtra("price")
                map["address"]       = intent.getStringExtra("address")
                map["kurir_id"]      = "1"
                map["distance"]      = intent.getStringExtra("distance")

                presenter.sale(map)
            }else {
                failedDialog()
            }
        }
    }

    private fun successDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_konfirmasi_success)

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val dialogButton = dialog.findViewById(R.id.btnKonfirmasiDialog) as Button
        dialogButton.setOnClickListener {
            finish()
        }
    }

    private fun failedDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_konfirmasi_failed)

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val dialogButton = dialog.findViewById(R.id.btnKonfirmasiDialog) as Button
        dialogButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun jual(status: Int) {
        successDialog()
    }

    override fun onError(message: String) {
        this.toast(message)
    }
}
