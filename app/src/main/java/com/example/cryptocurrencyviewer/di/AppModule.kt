package com.example.cryptocurrencyviewer.di

import android.content.Context
import com.example.cryptocurrencyviewer.data.api.CryptoApiService
import com.example.cryptocurrencyviewer.data.api.RetrofitClient
import com.example.cryptocurrencyviewer.data.CryptoRepositoryImpl
import com.example.cryptocurrencyviewer.data.db.RepositoryDataBase
import com.example.cryptocurrencyviewer.domain.CryptoRepository
import com.example.cryptocurrencyviewer.domain.usecases.GetCryptoPricesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideRepositoryDataBase(@ApplicationContext context: Context): RepositoryDataBase {
        return RepositoryDataBase(context)
    }

    @Provides
    @Singleton
    fun provideCryptoRepository(apiService: CryptoApiService, dbRepository: RepositoryDataBase): CryptoRepository {
        return CryptoRepositoryImpl(apiService, dbRepository)
    }

    @Provides
    @Singleton
    fun provideGetCryptoPricesUseCase(repository: CryptoRepository): GetCryptoPricesUseCase {
        return GetCryptoPricesUseCase(repository)
    }
}