package com.jefree.app.model.order

import com.google.gson.annotations.SerializedName
import com.jefree.app.model.ErrorResponse

data class AllOrderResponse(
        @SerializedName("data")
        val data: List<Order>?,
        @SerializedName("error")
        val error: ErrorResponse?,
        @SerializedName("status")
        val status: String?
)