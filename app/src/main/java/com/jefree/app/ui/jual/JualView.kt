package com.jefree.app.ui.jual

import com.jefree.app.model.Profile.Data
import com.jefree.app.model.Profile.Profile

interface JualView {
    fun onError(message: String)
    fun onSuccess(data: Data, status: Int)
    fun onSale(price: String)
}