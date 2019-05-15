package com.jefree.app.ui.home.activity

import com.jefree.app.JefreeApp
import com.jefree.app.model.Profile.EditProfileResponse
import com.jefree.app.ui.home.EditView
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfilePresenter(private val view: EditView) {
    fun editUser(userId: Int, map: Map<String, RequestBody>,avatar : MultipartBody.Part?) {
        view.showProgresEdit(true)
        println("MAP${map}")
        JefreeApp.api.editUser(userId, map, avatar)
            .enqueue(object : Callback<EditProfileResponse> {
                override fun onFailure(call: Call<EditProfileResponse>, t: Throwable) {
                    view.message(t.localizedMessage)
                    t.printStackTrace()
                    view.showProgresEdit(false)
                }

                override fun onResponse(call: Call<EditProfileResponse>, response: Response<EditProfileResponse>) {
                    val success = response.body()?.status == "OK"
                    if (success) {
                        view.editProfile(success, "sukses edit data profile")
                        view.showProgresEdit(true)
                    } else {
                        view.editProfile(success, "gagal edit profile")
                        view.showProgresEdit(false)
                    }

                }

            })
    }
}