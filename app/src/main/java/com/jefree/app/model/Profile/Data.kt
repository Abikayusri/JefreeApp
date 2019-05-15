package com.jefree.app.model.Profile

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @SerializedName("order")
    val order: Order?,
    @SerializedName("profile")
    val profile: Profile?
):Parcelable