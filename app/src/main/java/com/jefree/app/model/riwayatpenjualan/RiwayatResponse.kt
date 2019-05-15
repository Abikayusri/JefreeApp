package com.jefree.app.model.riwayatpenjualan

import com.google.gson.annotations.SerializedName
import com.jefree.app.model.ErrorResponse

data class RiwayatResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("data")
    val list: MutableList<RiwayatPenjualan>,
    @SerializedName("error")
    val error: ErrorResponse?
)