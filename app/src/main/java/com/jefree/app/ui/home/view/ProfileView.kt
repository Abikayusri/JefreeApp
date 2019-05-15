package com.jefree.app.ui.home.view

import com.jefree.app.model.Profile.Data

interface ProfileView {
    fun showUser(data : Data)
    fun message(message:String)
    fun proggress(boolean: Boolean)
}
