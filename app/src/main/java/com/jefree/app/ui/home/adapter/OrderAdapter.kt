package com.jefree.app.ui.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jefree.app.JefreeApp
import com.jefree.app.R
import com.jefree.app.common.TimeUtills
import com.jefree.app.model.Profile.ProfileResponse
import com.jefree.app.model.order.Order
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.item_divider_date.view.*
import kotlinx.android.synthetic.main.itemstudents.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderAdapter(
    val itemList: List<Any>,
    private val onClick: (Order) -> Unit
) : RecyclerView.Adapter<OrderAdapter.UserHolder>() {
    override fun onCreateViewHolder(group: ViewGroup, type: Int): UserHolder {
        val inflater = LayoutInflater.from(group.context)
        val view = if (type == 0) {
            inflater.inflate(R.layout.itemstudents, group, false)
        } else {
            inflater.inflate(R.layout.item_divider_date, group, false)
        }
        return UserHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemList[position] is Order) {
            0
        } else {
            1
        }
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
        if (item is Order) {
            holder.itemView.setOnClickListener {
                onClick(item)
            }
        }
    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Any) {
            //is kalo java instance of
            if (item is Order) {
                itemView.run {
                    tv_item_name.text = item.name
                    tv_jam.text = TimeUtills.convert(item.createdAt, "HH:mm a")
                    tv_item_deskription.text = "telah berhasil menyelamatkan lingkungan dengan mengumpulkan minyak jelantah sebanyak ${item.quantity}kg."
                    Glide.with(img_item_photo.context).load("https://res.cloudinary.com/enrahmad/"+item?.avatar).into(img_item_photo)
//
                }
            } else {
                itemView.run {
                    tv_date.text = item as String
                }
            }
        }
    }
}
