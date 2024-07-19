package com.team.currencyconverter.data.network.api

import com.team.currencyconverter.data.network.dto.ListСurrencyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?apikey=cur_live_8fqVDoMsgOB3VTaTE3aM7eHW11GF12bouIl5FDcT&base_currency=RUB")
    suspend fun getAllСurrency(): ListСurrencyDto

    @GET("?apikey=cur_live_8fqVDoMsgOB3VTaTE3aM7eHW11GF12bouIl5FDcT")
    suspend fun getInfoConvertСurrency(
        @Query("base_currency") baseCurrency: String
    ): ListСurrencyDto
}