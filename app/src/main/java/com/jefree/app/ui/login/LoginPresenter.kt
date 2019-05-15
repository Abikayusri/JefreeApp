package com.jefree.app.ui.login

import com.jefree.app.JefreeApp
import com.jefree.app.model.ErrorResponse
import com.jefree.app.model.LoginResponse
import com.jefree.app.model.dataLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private val view: LoginView) {
    fun checkLogin(map: Map<String, String>) {
        view.showProgressLogin(true)
        JefreeApp.api.login(map)
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    view.onLoginView(false, t.localizedMessage)
                    view.showProgressLogin(false)
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val onSucces = response.body()?.status == "OK"
                    if (onSucces) {
                        val dataLogin: dataLogin? = response.body()?.data
                        val tokenSucces = dataLogin?.token
                        JefreeApp.sp.token = tokenSucces!!
                        val id: Int = dataLogin.userId!!
                        JefreeApp.sp.id = id
                    //    println("token :"+tokenSucces)
                        view.onLoginView(onSucces, "sukses Login")
                        view.showProgressLogin(true)
                    } else {
                        if (response.body() != null) {
                            val errorMessage: ErrorResponse = response.body()?.error!!
                            view.onLoginView(onSucces, errorMessage.message)
                            view.showProgressLogin(false)
                        }
                    }
                }
            })
    }
}

