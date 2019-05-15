package com.jefree.app.ui.home.view

import com.jefree.app.model.riwayatpenjualan.RiwayatPenjualan

interface RiwayatView {
    fun onError(message: String)
    fun onSuccess(data: List<RiwayatPenjualan>)
    fun showProgrees(show: Boolean)
}