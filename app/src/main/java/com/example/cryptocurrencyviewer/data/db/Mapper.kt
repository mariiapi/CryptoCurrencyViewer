package com.example.cryptocurrencyviewer.data.db

import com.example.cryptocurrencyviewer.domain.CryptoItem

fun CryptoItem.toCryptoEntity() : CryptoEntity {
    return CryptoEntity(
        name = name,
        exchangeRate = exchangeRate,
        lastUpdate = lastUpdate,
        min24h = min24h,
        max24h = max24h,
        imageURL = imageURL
    )
}