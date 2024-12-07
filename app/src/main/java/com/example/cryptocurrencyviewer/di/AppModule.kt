package com.example.cryptocurrencyviewer.di

import com.example.cryptocurrencyviewer.data.CryptoApiService
import com.example.cryptocurrencyviewer.data.RetrofitClient
import com.example.cryptocurrencyviewer.data.CryptoRepositoryImpl
import com.example.cryptocurrencyviewer.domain.CryptoRepository
import com.example.cryptocurrencyviewer.domain.usecases.GetCryptoPricesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCryptoApiService(): CryptoApiService {
        return RetrofitClient.instance
    }

    @Provides
    @Singleton
    fun provideCryptoRepository(apiService: CryptoApiService): CryptoRepository {
        return CryptoRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetCryptoPricesUseCase(repository: CryptoRepository): GetCryptoPricesUseCase {
        return GetCryptoPricesUseCase(repository)
    }
}