package com.jefree.app

import android.app.Application
import com.jefree.app.data.PreferenceHelper
import com.jefree.app.network.ApiService
import com.jefree.app.network.NetworkConfig

class JefreeApp:Application() {

    companion object {
        lateinit var api: ApiService
        lateinit var sp: PreferenceHelper
    }

    override fun onCreate() {
        super.onCreate()
        api = NetworkConfig.getRetrofit().create(ApiService::class.java)
        sp = PreferenceHelper(this)

    }
}