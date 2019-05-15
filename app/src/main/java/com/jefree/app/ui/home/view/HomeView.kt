package com.jefree.app.ui.home.view
import com.jefree.app.model.order.Order

interface HomeView {
    fun showOrder(results: List<Order>?)
    fun onError(message :String)
    fun showProgrees(show:Boolean)

}