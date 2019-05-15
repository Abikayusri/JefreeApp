package com.jefree.app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
@Parcelize
data class LoginResponse(
    @SerializedName("data")
    val data: dataLogin?,
    @SerializedName("error")
    val error: ErrorResponse?,
    @SerializedName("status")
    val status: String?
):Parcelable