package com.team.currencyconverter.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.team.currencyconverter.domain.usecase.GetAllСurrencyItemUseCase
import com.team.currencyconverter.domain.usecase.GetConvertCurrencyUseCase

class MainViewModelFactory(
    val getAllСurrencyItemUseCase: GetAllСurrencyItemUseCase,
    val getConvertCurrencyUseCase: GetConvertCurrencyUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getAllСurrencyItemUseCase = getAllСurrencyItemUseCase,
            getConvertCurrencyUseCase = getConvertCurrencyUseCase
        ) as T
    }
}