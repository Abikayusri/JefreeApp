package com.jefree.app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class dataLogin(
    @SerializedName("user_id")
    var userId: Int?,
    @SerializedName("token")
    var token: String?
):Parcelable