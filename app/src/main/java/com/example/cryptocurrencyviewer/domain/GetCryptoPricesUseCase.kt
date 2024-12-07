package com.example.cryptocurrencyviewer.domain

class GetCryptoPricesUseCase(private val repository: CryptoRepository) {
    suspend operator fun invoke(): List<CryptoItem> {
        return repository.getAllCryptoPrices()
    }
}