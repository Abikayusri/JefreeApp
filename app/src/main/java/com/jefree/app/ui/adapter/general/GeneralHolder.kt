package com.jefree.app.ui.adapter.general

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jefree.app.config.GeneralKey
import com.jefree.app.ui.aboutUs.list_more.GdetailActivity
import kotlinx.android.synthetic.main.list_card.view.*

class GeneralHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val cardview = view.card_view
    private val textview = view.text_view_card


    fun bindData(context: Context, pos: Int, string: String, mkey: String) {
        textview.text = string
        cardview.setOnClickListener {
            goDetail(context, pos, mkey)
        }
    }

    fun goDetail(context: Context, pos: Int, mkey: String) {
        var intent = Intent(context, GdetailActivity::class.java)
        intent.putExtra(GeneralKey.pos, pos)
        intent.putExtra(GeneralKey.key, mkey)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }


}