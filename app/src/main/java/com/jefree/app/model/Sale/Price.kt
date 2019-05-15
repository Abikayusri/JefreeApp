package com.jefree.app.model.Sale

import com.google.gson.annotations.SerializedName

data class Price (
        @SerializedName("price")
        val price: Double?
)