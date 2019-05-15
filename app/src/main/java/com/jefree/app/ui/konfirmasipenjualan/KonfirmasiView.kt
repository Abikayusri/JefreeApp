package com.jefree.app.ui.konfirmasipenjualan

interface KonfirmasiView {
    fun onError(message: String)
    fun jual(status: Int)
}