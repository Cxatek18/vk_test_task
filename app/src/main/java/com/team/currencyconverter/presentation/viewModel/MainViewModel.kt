package com.team.currencyconverter.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team.currencyconverter.domain.usecase.GetAllСurrencyItemUseCase
import com.team.currencyconverter.domain.usecase.GetConvertCurrencyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val getAllСurrencyItemUseCase: GetAllСurrencyItemUseCase,
    val getConvertCurrencyUseCase: GetConvertCurrencyUseCase
) : ViewModel() {

    private val _currencyItemList = MutableStateFlow<List<String>>(emptyList())
    val currencyItemList = _currencyItemList.asStateFlow()

    private val _currencyConvertString = MutableStateFlow<String>("")
    val currencyConvertString = _currencyConvertString.asStateFlow()

    fun getCurrencyItemList() {
        viewModelScope.launch {
            getAllСurrencyItemUseCase.getAllСurrencyItem()
                .collect { listCurrency ->
                    _currencyItemList.value = listCurrency
                }
        }
    }

    fun convertCurrency(
        baseCurrency: String,
        convertCurrency: String,
        amountOfCurrency: Int
    ) {
        viewModelScope.launch {
            getConvertCurrencyUseCase.getConvertCurrency(
                baseCurrency = baseCurrency,
                convertCurrency = convertCurrency,
                amountOfCurrency = amountOfCurrency
            ).collect { resultConvert ->
                _currencyConvertString.value = resultConvert
            }
        }
    }
}