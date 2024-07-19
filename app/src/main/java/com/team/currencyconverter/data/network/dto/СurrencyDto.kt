package com.team.currencyconverter.data.network.dto

import com.google.gson.annotations.SerializedName

data class СurrencyDto(
    @SerializedName("code")
    val code: String,
    @SerializedName("value")
    val value: Float
)
