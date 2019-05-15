package com.jefree.app.model.order

import com.jefree.app.model.ErrorResponse
import com.google.gson.annotations.SerializedName

data class OrderResponse(
        @SerializedName("data")
        val data: Order?,
        @SerializedName("error")
        val error: ErrorResponse?,
        @SerializedName("status")
        val status: String?
)