package com.team.currencyconverter.di

import com.team.currencyconverter.data.network.api.ApiService
import com.team.currencyconverter.data.repository.СurrencyItemRepositoryImpl
import com.team.currencyconverter.domain.repository.СurrencyItemRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun provideUserApiService(client: OkHttpClient): ApiService {
        val BASE_URL = "https://api.currencyapi.com/v3/latest/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        return okHttpClient
    }

    @Provides
    fun provideСurrencyItemRepository(
        apiService: ApiService
    ): СurrencyItemRepository {
        return СurrencyItemRepositoryImpl(apiService)
    }
}