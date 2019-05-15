package com.jefree.app.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
//import com.example.oilink.OilinkApp.Companion.context
import com.jefree.app.R
import com.jefree.app.ui.adapter.general.GeneralHolder

class GeneralAdapter(private val mcontext: Context,private val datas: List<String>, private val mkey:String) : RecyclerView.Adapter<GeneralHolder>() {



    override fun onCreateViewHolder(viewGrup: ViewGroup, p1: Int): GeneralHolder {
        return GeneralHolder(LayoutInflater.from(viewGrup.context).inflate(R.layout.list_card,viewGrup,false))
    }

    override fun getItemCount(): Int {

       return datas.size
    }

    override fun onBindViewHolder(holder: GeneralHolder, pos: Int) {
        holder.bindData(mcontext , pos , datas[pos],mkey)
    }

}