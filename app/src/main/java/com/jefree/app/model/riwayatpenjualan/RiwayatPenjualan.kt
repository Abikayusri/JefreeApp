package com.jefree.app.model.riwayatpenjualan

import com.google.gson.annotations.SerializedName

data class RiwayatPenjualan(
    @SerializedName("id")
    val id: String?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("note")
    val note: String?,

    @SerializedName("address")
    val address: String?,

    @SerializedName("quantity")
    val quantity: String?,

    @SerializedName("price")
    val price: String?,

    @SerializedName("created_at")
    val created_at: String?
)