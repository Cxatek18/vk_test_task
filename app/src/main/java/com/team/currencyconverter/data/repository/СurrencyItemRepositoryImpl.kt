package com.team.currencyconverter.data.repository

import com.team.currencyconverter.data.mapper.toListCurrencyEntity
import com.team.currencyconverter.data.network.api.ApiService
import com.team.currencyconverter.domain.repository.СurrencyItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class СurrencyItemRepositoryImpl(
    val apiService: ApiService
) : СurrencyItemRepository {

    private val currencyItemList = MutableStateFlow<List<String>>(emptyList())
    private val convertCurrencyResponse = MutableStateFlow<String>("")

    override fun getAllСurrencyItem(): Flow<List<String>> =
        flow<List<String>> {
            currencyItemList.value = apiService
                .getAllСurrency()
                .data
                .toListCurrencyEntity()
                .map {
                    it.code
                }
            currencyItemList.collect { emit(it) }
        }

    override fun getConvertCurrency(
        baseCurrency: String,
        convertCurrency: String,
        amountOfCurrency: Int
    ): Flow<String> = flow<String> {
        val convertingCurrency = apiService
            .getInfoConvertСurrency(convertCurrency)
            .data
            .toListCurrencyEntity()
            .find { it.code == baseCurrency }
        convertCurrencyResponse.value = (
                convertingCurrency?.value?.times(amountOfCurrency)
                ).toString()
        convertCurrencyResponse.collect { emit(it) }
    }
}