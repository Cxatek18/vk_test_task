package com.team.currencyconverter.di

import com.team.currencyconverter.domain.repository.СurrencyItemRepository
import com.team.currencyconverter.domain.usecase.GetAllСurrencyItemUseCase
import com.team.currencyconverter.domain.usecase.GetConvertCurrencyUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetAllСurrencyItemUseCase(
        repository: СurrencyItemRepository
    ): GetAllСurrencyItemUseCase {
        return GetAllСurrencyItemUseCase(repository)
    }

    @Provides
    fun provideGetConvertCurrencyUseCase(
        repository: СurrencyItemRepository
    ): GetConvertCurrencyUseCase {
        return GetConvertCurrencyUseCase(repository)
    }
}