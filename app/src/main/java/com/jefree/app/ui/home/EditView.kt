package com.jefree.app.ui.home

interface EditView{
    fun editProfile(success : Boolean, message: String)
    fun message(message :String)
    fun showProgresEdit(show:Boolean)
}