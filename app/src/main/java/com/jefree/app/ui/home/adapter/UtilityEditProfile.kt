package com.jefree.app.ui.home.adapter

import java.util.*

internal class UtilityEditProfile {
    companion object {
        val CountryDataSourceEdit: List<SpinnerItemEditProfile> = object : ArrayList<SpinnerItemEditProfile>() {
            init {
                add(SpinnerItemEditProfile("01", "Rumah Tangga", "RT"))
                add(SpinnerItemEditProfile("02", "UMKM", "UMKM"))
                add(SpinnerItemEditProfile("03", "Warung atau Rumah Makan", "WR"))
                add(SpinnerItemEditProfile("04", "Restoran atau Cafe", "RS"))
                add(SpinnerItemEditProfile("05", "Katering", "KT"))
            }
        }
    }
}