package com.jefree.app.model.order

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.jefree.app.common.TimeUtills
import com.jefree.app.model.Profile.Avatar
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Order(
        @SerializedName("name")
        val name: String?,
        @SerializedName("avatar")
        val avatar: String?,
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("delivery_date")
        val deliveryDate: String?,
        @SerializedName("distance")
        val distance: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("latitude")
        val latitude: String?,
        @SerializedName("longitude")
        val longitude: String?,
        @SerializedName("note")
        val note: String?,
        @SerializedName("price")
        val price: Int?,
        @SerializedName("quantity")
        val quantity: String?,
        @SerializedName("status")
        val status: String?,
        @SerializedName("updated_at")
        val updatedAt: String?,
        @SerializedName("user_id")
        val userId: Int?,
        var date: Date = Date()
) : Parcelable, Comparable<Order> {
        override fun compareTo(other: Order): Int {
                val otherDate = TimeUtills.toDate(other.createdAt)
                return date.compareTo(otherDate)
        }
}