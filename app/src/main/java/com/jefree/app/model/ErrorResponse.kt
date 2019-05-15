package com.jefree.app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//error struktur di api
@Parcelize
class ErrorResponse(
    @field:SerializedName("message")
    val message: String
):Parcelable
