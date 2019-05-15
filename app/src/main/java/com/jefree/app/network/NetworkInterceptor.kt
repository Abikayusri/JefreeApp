package com.jefree.app.network

import com.jefree.app.JefreeApp
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = JefreeApp.sp.token //ngambil dari shared pref
        /*menambahkan header auth uk setiap request*/
        val modifyRequest: Request? = chain.request().newBuilder()
            .addHeader("Authorization", "bearer $token")
            .build()

        val mResponse: Response = chain.proceed(modifyRequest!!)
        /*jika token expired maka code = 401*/
        /*if (mResponse.code() == 401) {
            *//*ambil token baru dari objek mResponse
            lalu lakukan ulang request*//*
            val newToken = "yyyy" //ambil dari mResponse
            val newRequest: Request? = chain.request().newBuilder()
                .addHeader("Authorization", "bearer $newToken")
                .build()

            return chain.proceed(newRequest!!)
        }*/

        return mResponse
    }
}