package com.jefree.app.ui.jual

import com.jefree.app.JefreeApp
import com.jefree.app.model.Profile.ProfileResponse
import com.jefree.app.model.Sale.CalculateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JualPresenter(val view: JualView) {
    fun showData() {
        JefreeApp.api.getSingleUser(JefreeApp.sp.id)
                .enqueue(object : Callback<ProfileResponse> {
                    override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                        view.onError("Gagal terhubung server")
                    }

                    override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {

                        if (response.body() != null) {
                            val data = response.body()!!.data
                            if (data?.profile?.address != null) {
                                println("alamat:" + data.profile.address)
                                view.onSuccess(data,1)
                            } else {
                                view.onSuccess(data!!,0)
                            }
                        } else {
                            view.onError("Gagal terhubung server")
                        }
                    }
                })
    }

    fun sale(map: Map<String, String>) {
        JefreeApp.api.calculate(map)
            .enqueue(object : Callback<CalculateResponse> {
                override fun onFailure(call: Call<CalculateResponse>, t: Throwable) {
                    view.onError("Gagal terhubung server")
                }

                override fun onResponse(call: Call<CalculateResponse>, response: Response<CalculateResponse>) {
                    val data = response.body()
                    if(data != null){
                        view.onSale(data.data!!.price!!.toInt().toString())
                    }else{
                        view.onError("Gagal terhubung server")
                    }
                }
            })
    }
}