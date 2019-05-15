package com.jefree.app.ui.login

interface LoginView {
    fun onLoginView(status: Boolean, message: String = ""){
    }
    fun showProgressLogin(show:Boolean)
}