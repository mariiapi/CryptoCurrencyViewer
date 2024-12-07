package com.example.cryptocurrencyviewer.domain

interface CryptoRepository {
    suspend fun getAllCryptoPrices(): List<CryptoItem>
}
