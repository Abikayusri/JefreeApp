package com.jefree.app.model.Profile

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(
    @SerializedName("address")
    val address: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("bussiness_type")
    val bussinessType: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("avatar")
    val avatar: Avatar?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("password_digest")
    val passwordDigest: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("role")
    val role: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("verification_code")
    val verificationCode: String?
):Parcelable