package com.jefree.app.model.Sale

import com.jefree.app.model.ErrorResponse
import com.google.gson.annotations.SerializedName

data class CalculateResponse (
    @SerializedName("data")
    val data: Price?,
    @SerializedName("error")
    val error: ErrorResponse?,
    @SerializedName("status")
    val status: String?
)