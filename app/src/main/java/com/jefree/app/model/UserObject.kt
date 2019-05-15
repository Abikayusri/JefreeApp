package com.jefree.app.model

import com.google.gson.annotations.SerializedName

data class UserObject(
    @SerializedName("data")
    val data: List<User>,
  //  val data: User,
    @SerializedName("error")
    val error: Any?,
    @SerializedName("status")
    val status: String?
)