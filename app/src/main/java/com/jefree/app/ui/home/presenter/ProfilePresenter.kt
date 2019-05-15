package com.jefree.app.ui.home.presenter

import com.jefree.app.JefreeApp
import com.jefree.app.model.Profile.ProfileResponse
import com.jefree.app.ui.home.view.ProfileView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePresenter(private val view: ProfileView) {
    fun showUser(userId: Int) {
        view.proggress(true)
        println("userid" + JefreeApp.sp.id)
        println("token" + JefreeApp.sp.token)
        JefreeApp.api.getSingleUser(userId)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    view.message(t.localizedMessage)
                    view.proggress(false)
                }
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                val data = response.body()
                view.proggress(false)
                if (data != null) {
                    println("test profile" + data.data!!.profile!!.name)
                    view.showUser(data.data)

                } else {
                    view.message("gagal memuat data")

                }
            }

        })
    }
}