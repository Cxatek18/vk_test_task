package com.team.currencyconverter.domain.repository

import kotlinx.coroutines.flow.Flow

interface СurrencyItemRepository {

    fun getAllСurrencyItem(): Flow<List<String>>

    fun getConvertCurrency(
        baseCurrency: String,
        convertCurrency: String,
        amountOfCurrency: Int
    ): Flow<String>
}