package com.team.currencyconverter.di

import com.team.currencyconverter.domain.usecase.GetAllСurrencyItemUseCase
import com.team.currencyconverter.domain.usecase.GetConvertCurrencyUseCase
import com.team.currencyconverter.presentation.viewModel.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideMainViewModelFactory(
        getAllСurrencyItemUseCase: GetAllСurrencyItemUseCase,
        getConvertCurrencyUseCase: GetConvertCurrencyUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getAllСurrencyItemUseCase = getAllСurrencyItemUseCase,
            getConvertCurrencyUseCase = getConvertCurrencyUseCase
        )
    }
}