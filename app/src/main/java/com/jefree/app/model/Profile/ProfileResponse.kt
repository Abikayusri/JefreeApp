package com.jefree.app.model.Profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("data")
    val data: Data?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("status")
    val status: String?
)

data class EditProfileResponse(
    @SerializedName("data")
    val data: Profile?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("status")
    val status: String?
)