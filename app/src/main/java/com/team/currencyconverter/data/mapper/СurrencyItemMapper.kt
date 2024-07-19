package com.team.currencyconverter.data.mapper

import com.team.currencyconverter.data.network.dto.СurrencyDto
import com.team.currencyconverter.domain.entity.СurrencyItem

fun Map<String, СurrencyDto>.toListCurrencyEntity(): List<СurrencyItem> = values.map {
    СurrencyItem(code = it.code, value = it.value)
}