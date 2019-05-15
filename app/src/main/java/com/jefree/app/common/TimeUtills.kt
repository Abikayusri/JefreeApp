package com.jefree.app.common

import java.text.SimpleDateFormat
import java.util.*

object TimeUtills {

    fun toDate(dateStr: String?): Date {
        if (dateStr.isNullOrBlank()) return Date()
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        return sdf.parse(dateStr)
    }

//Yang digunakan di adapter
    fun convert(date: String?, pattern: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val nDate = sdf.parse(date)
        val nSdf = SimpleDateFormat(pattern, Locale.getDefault())
        return nSdf.format(nDate)
    }

    fun convert(date: Date?, pattern: String): String {
        val nSdf = SimpleDateFormat(pattern, Locale.getDefault())
        return nSdf.format(date)
    }
}