package com.example.cryptocurrencyviewer.domain

import java.io.Serializable

data class CryptoItem(
    val name: String,
    val exchangeRate: Double,
    val lastUpdate: String,
    val min24h: Double?,
    val max24h: Double?
) : Serializable