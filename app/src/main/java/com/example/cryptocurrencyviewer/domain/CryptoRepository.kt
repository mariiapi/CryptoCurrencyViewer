package com.example.cryptocurrencyviewer.domain

interface CryptoRepository {
    suspend fun getTopCryptos(): Result<List<CryptoItem>>
}