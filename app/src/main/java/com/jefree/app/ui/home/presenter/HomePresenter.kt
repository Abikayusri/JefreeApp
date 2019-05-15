package com.jefree.app.ui.home.presenter

import com.jefree.app.JefreeApp
import com.jefree.app.model.order.AllOrderResponse
import com.jefree.app.ui.home.view.HomeView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(private val view: HomeView) {

    private val apiService by lazy {
        JefreeApp.api
    }

    fun getOrder() {
        //view.showProgrees(true)
        apiService.getOrders()
                .enqueue(object : Callback<AllOrderResponse> {
                    override fun onFailure(call: Call<AllOrderResponse>, t: Throwable) {
                        view.onError("Gagal terhubung server!")
                        view.showProgrees(false)
                    }

                    override fun onResponse(call: Call<AllOrderResponse>, response: Response<AllOrderResponse>) {
                        view.showProgrees(false)
                        val data = response.body()
                        if(data != null) {
                            println("data order"+ data.data!![0].id)
                            view.showOrder(data.data)
                        }else {
                            view.onError("Gagal terhubung server")
                        }
                    }
                })
    }
}