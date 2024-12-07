package com.example.cryptocurrencyviewer.domain.usecases

import com.example.cryptocurrencyviewer.domain.CryptoItem
import com.example.cryptocurrencyviewer.domain.CryptoRepository

class GetCryptoPricesUseCase(private val repository: CryptoRepository) {
    suspend operator fun invoke(): Result<List<CryptoItem>> {
        return repository.getTopCryptos()
    }
}