package com.team.currencyconverter.data.network.dto

import com.google.gson.annotations.SerializedName

data class ListСurrencyDto(
    @SerializedName("data")
    val data: Map<String, СurrencyDto>
)
