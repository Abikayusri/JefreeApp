package com.jefree.app.common

import com.google.gson.Gson
import com.google.gson.JsonElement
import java.text.NumberFormat
import java.util.*

//T akan diisi userResponse

val Any.toJson: JsonElement
    get() = Gson().toJsonTree(this)

fun currencyFormat(amount: Double): String {
    val localeID = Locale("in", "ID")

    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    return " " + formatRupiah.format(amount)
}