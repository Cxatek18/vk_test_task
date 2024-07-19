package com.team.currencyconverter.domain.usecase

import com.team.currencyconverter.domain.repository.СurrencyItemRepository
import kotlinx.coroutines.flow.Flow

class GetAllСurrencyItemUseCase(private val repository: СurrencyItemRepository) {

    fun getAllСurrencyItem(): Flow<List<String>> {
        return repository.getAllСurrencyItem()
    }
}