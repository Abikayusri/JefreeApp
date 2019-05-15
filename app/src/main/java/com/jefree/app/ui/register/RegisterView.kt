package com.jefree.app.ui.register

interface RegisterView  {
    fun register(Status : Boolean, message :String = "" )
    fun OnError(message: String)
    fun showProgresRegister(show:Boolean)
}