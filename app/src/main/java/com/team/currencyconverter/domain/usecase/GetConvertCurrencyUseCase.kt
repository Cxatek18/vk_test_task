package com.team.currencyconverter.domain.usecase

import com.team.currencyconverter.domain.repository.СurrencyItemRepository
import kotlinx.coroutines.flow.Flow

class GetConvertCurrencyUseCase(private val repository: СurrencyItemRepository) {

    fun getConvertCurrency(
        baseCurrency: String,
        convertCurrency: String,
        amountOfCurrency: Int
    ): Flow<String> {
        return repository.getConvertCurrency(
            baseCurrency = baseCurrency,
            convertCurrency = convertCurrency,
            amountOfCurrency = amountOfCurrency
        )
    }
}