package com.jefree.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import com.jefree.app.JefreeApp
import com.jefree.app.R
import com.jefree.app.common.toast
import com.jefree.app.ui.home.activity.Home

import com.jefree.app.ui.register.Register
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity(), LoginView {

    private var showPassword = false
    private val presenter = LoginPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnBack()
        setupListener()
    }

    private fun btnBack() {
        //actionbar
        setSupportActionBar(toolbarLogin)
        supportActionBar?.title = "Masuk"
        //set back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setupListener() {
        toggle_show_password.setOnClickListener {
            showPassword()
        }
        tv_login_daftar.setOnClickListener {
            Daftar()
        }
        btn_log_login.setOnClickListener {
            Masuk()
        }
    }

    private fun showPassword() {
        if (!showPassword) {
            showPassword = true
            et_login_password.transformationMethod = null
            if (et_login_password.text.length >= 0) {
                et_login_password.setSelection(et_login_password.text.length)
                toggle_show_password.setImageDrawable(resources.getDrawable(R.drawable.ic_eye_hide))
            }
        } else {
            showPassword = false
            et_login_password.transformationMethod = PasswordTransformationMethod()
            if (et_login_password.text.length >= 0) {
                et_login_password.setSelection(et_login_password.text.length)
                toggle_show_password.setImageDrawable(resources.getDrawable(R.drawable.ic_show_password))
            }
        }
    }

    private fun Masuk() {
        val phone = et_login_no_handphone.text.toString()
        val password = et_login_password.text.toString()

        if (phone.isBlank() || password.isBlank()) {
            Toast.makeText(this, "User atau Password tidak boleh kosong", Toast.LENGTH_SHORT)
                .show()
            return
        } else {
            JefreeApp.sp.phone = phone
            JefreeApp.sp.pass = password
            JefreeApp.sp.login = true
            val map = mutableMapOf<String, String>()
            map["phone"] = phone
            map["password"] = password
            presenter.checkLogin(map)

        }
    }

    override fun showProgressLogin(show: Boolean) {
        if (show) {
            progressBar_login?.visibility = View.VISIBLE

        } else
            progressBar_login?.visibility = View.GONE
    }

    private fun Daftar() {
        val intent = Intent(this@Login, Register::class.java)
        startActivity(intent)
        finish()
    }

    override fun onLoginView(status: Boolean, message: String) {
        toast(message)
        if (status) {
            // problem = put extra tidak masuk ke database
            val token = JefreeApp.sp.token
            //  intent.putExtra(Constant.USERS, user)
            finish()
        }
    }

}
