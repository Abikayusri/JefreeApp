package com.jefree.app.ui.home.presenter

import com.jefree.app.JefreeApp
import com.jefree.app.model.Profile.ProfileResponse
import com.jefree.app.model.riwayatpenjualan.RiwayatResponse
import com.jefree.app.ui.home.view.RiwayatView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatPresenter(val view: RiwayatView) {

    fun showRiwayat() {
        view.showProgrees(true)
        JefreeApp.api.getRiwayat(JefreeApp.sp.id)
            .enqueue(object :Callback<RiwayatResponse>{
                override fun onFailure(call: Call<RiwayatResponse>, t: Throwable) {
                    view.onError("Gagal terhubung server")
                    view.showProgrees(false)
                }

                override fun onResponse(call: Call<RiwayatResponse>, response: Response<RiwayatResponse>) {
                    view.showProgrees(false)
                    val data = response.body()
                    if (data != null) {
                        view.onSuccess(data.list)
                    } else {
                        view.onError("Anda belum melakukan order")
                    }
                }
            })
    }

    fun getDataUser() {
        JefreeApp.api.getSingleUser(JefreeApp.sp.id)
            .enqueue(object : Callback<ProfileResponse>{
                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    println("error")
                }

                override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                    val data = response.body()
                    if(data != null) {
                        JefreeApp.sp.name = data.data!!.profile!!.name!!
                    }
                }
            })
    }
}