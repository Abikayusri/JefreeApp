package com.jefree.app.model

import android.os.Parcelable
import com.jefree.app.model.Profile.Avatar
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_path")
    val avatar: Avatar?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("password_digest")
    val passwordDigest: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("role")
    val role: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("verification_code")
  //  val verificationCode:  Any?
    val verificationCode:  String?
):Parcelable
{
}