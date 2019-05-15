package com.jefree.app.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val data:UserObject,
    @SerializedName("error")
    val error:ErrorResponse?,
    @SerializedName("status")
    val status: String?
)