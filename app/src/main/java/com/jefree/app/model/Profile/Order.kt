package com.jefree.app.model.Profile

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
    @SerializedName("total_oil")
    val totalOil: String?,
    @SerializedName("total_order")
    val totalOrder: Int?
):Parcelable