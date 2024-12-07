package com.example.cryptocurrencyviewer.data

import com.example.cryptocurrencyviewer.domain.CryptoItem
import com.example.cryptocurrencyviewer.domain.CryptoRepository

class CryptoRepositoryImpl(private val apiService: CryptoApiService) : CryptoRepository {
    override suspend fun getAllCryptoPrices(): List<CryptoItem> {
        val response = apiService.getCryptoPrices()
        return response.map { CryptoItem(it.key, it.value["USD"] ?: 0.0) }
    }
}