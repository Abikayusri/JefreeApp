package com.jefree.app.ui.register.adapter

import java.util.*

internal class UtilityRegister {
    companion object {
        val CountryDataSourceRegist: List<SpinnerItemRegister> = object : ArrayList<SpinnerItemRegister>() {
            init {
//                add(SpinnerItemRegister("00", "Gelar", "NULL"))
                add(SpinnerItemRegister("01", "Bapak", "BP"))
                add(SpinnerItemRegister("02", "Ibu", "IB"))
                add(SpinnerItemRegister("03", "Mas", "MS"))
                add(SpinnerItemRegister("04", "Mba", "MB"))
            }
        }
    }
}