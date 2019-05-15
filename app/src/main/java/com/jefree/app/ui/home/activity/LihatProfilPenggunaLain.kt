package com.jefree.app.ui.home.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jefree.app.JefreeApp
import com.jefree.app.R
import com.jefree.app.common.Constant
import com.jefree.app.common.toast

import com.jefree.app.model.Profile.Data
import com.jefree.app.model.Profile.ProfileResponse
import com.jefree.app.model.order.Order

import kotlinx.android.synthetic.main.activity_lihat_profil_pengguna_lain.*
import kotlinx.android.synthetic.main.itemstudents.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LihatProfilPenggunaLain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_profil_pengguna_lain)
        setupView()
    }

    private fun setupView() {
        setSupportActionBar(toolbar3)
        supportActionBar?.title = "Profile"

        val order: Order? = intent.getParcelableExtra(Constant.ORDER)

        JefreeApp.api.getSingleUser(order!!.userId!!)
            .enqueue(object :Callback<ProfileResponse>{
                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    toast("Gagal terhubung server")
                }

                override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                    val data = response.body()
                    if(data != null){
                        namaUserLain.text = data.data?.profile?.name
                        trans.text = data.data?.order?.totalOrder.toString()
                        tvKg.text = data.data?.order?.totalOil
                        Glide.with(ccview2.context).load("https://res.cloudinary.com/enrahmad/"+order?.avatar).into(ccview2)
                    }else{
                        toast("Gagal terhubung server")
                    }
                }
            })




    }
}
