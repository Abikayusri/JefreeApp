package com.jefree.app.ui.konfirmasipenjualan

import com.jefree.app.JefreeApp
import com.jefree.app.model.order.OrderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KonfirmasiPresenter(val view: KonfirmasiView) {
    fun sale(map: Map<String, String>) {
        JefreeApp.api.createOrder(map)
                .enqueue(object : Callback<OrderResponse> {
                    override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                        view.onError("Gagal terhubung server")
                    }

                    override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                        val data = response.body()
                        if (data != null) {
                            view.jual(1)
                        }else {
                            view.onError("Gagal terhubung server")
                        }
                    }
                })

    }
}