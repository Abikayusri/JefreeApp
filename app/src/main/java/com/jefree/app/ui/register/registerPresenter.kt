package com.jefree.app.ui.register

import com.jefree.app.JefreeApp
import com.jefree.app.model.ErrorResponse
import com.jefree.app.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class registerPresenter(private val view: RegisterView) {
    fun addUser(map: Map<String, String>) {
//        view.showProgrees(true)
        JefreeApp.api.register(map)
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    view.register(false, t.localizedMessage)
                    view.showProgresRegister(false)
                }

                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val isSuccess = response.body()?.status == "OK"
                    if (isSuccess) {
                        view.register(isSuccess, "berhasil register")
                        view.showProgresRegister(true)
                    } else
                        if (response.body() != null) {
                            val errorMessage: ErrorResponse = response.body()!!.error!!
                            view.register(isSuccess, errorMessage.message)
                            view.showProgresRegister(false)
                        }
                }
            })

    }
}






