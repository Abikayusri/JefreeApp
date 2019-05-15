package com.jefree.app.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.jefree.app.common.Constant

class PreferenceHelper(app: Application) {
    private val sp: SharedPreferences by lazy {
        app.getSharedPreferences("binar_app", Context.MODE_PRIVATE)
    }


    private val spe: SharedPreferences.Editor by lazy {
        sp.edit()
    }


    var name: String
        set(value) = spe.putString(Constant.NAMA, value).apply()
        get() = sp.getString(Constant.NAMA, "") ?: ""

    var id: Int
        set(value) = spe.putInt(Constant.ID, value).apply()
        get() = sp.getInt(Constant.ID, 0) ?: 0

    var kategori: String
        set(value) = spe.putString(Constant.KATEGORI, value).apply()
        get() = sp.getString(Constant.KATEGORI, "") ?: ""

    var phone: String
        set(value) = spe.putString(Constant.PHONE, value).apply()
        get() = sp.getString(Constant.PHONE, "") ?: ""

    var pass: String
        set(value) = spe.putString(Constant.PASS, value).apply()
        get() = sp.getString(Constant.PASS, "") ?: ""

    var email: String
        set(value) = spe.putString(Constant.EMAIL, value).apply()
        get() = sp.getString(Constant.EMAIL, "") ?: ""

    var alamat: String
        set(value) = spe.putString(Constant.ALAMAT, value).apply()
        get() = sp.getString(Constant.ALAMAT, "") ?: ""

    var login: Boolean
        set(value) = spe.putBoolean(Constant.IS_LOGIN, value).apply()
        get() = sp.getBoolean(Constant.IS_LOGIN, false)

    var intro: Boolean
        set(value) = spe.putBoolean(Constant.IS_INTRO, value).apply()
        get() = sp.getBoolean(Constant.IS_INTRO, true)

    var token: String
        set(value) = spe.putString(Constant.TOKEN, value).apply()
        get() = sp.getString(Constant.TOKEN, "") ?: ""

    fun logOut() {
        spe.clear().apply()
    }

    /* fun saveUser(user: User) {
         spe.putInt("id", user.id!!)
         spe.putString("email", user.email)
         spe.putString("name", user.name)
         spe.putString("phone", user.phone)
         spe.apply()

     }
     val user: User
         get() {
             return User(
                 sp.getString("created_at", null),
                 sp.getString("email" , null),
                 sp.getInt("id", 0),
                 sp.getString("image_path",""),
                 sp.getString("name", ""),
                 sp.getString("password_digest",""),
                 sp.getString("phone",""),
                 sp.getString("role",""),
                 sp.getString("status",""),
                 sp.getString("update_at", ""),
                 sp.getString("verification_code","")

             )
         }*/


}