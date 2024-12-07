package com.example.cryptocurrencyviewer.domain

data class CryptoItem (
    val name : String,
    val exchangeRate: Double,
)

//data class CryptoItem (
//    val name : String,
//    val exchangeRate: Int,
//    val lastUpdate: Long = 0,
//    val min: Double,
//    val max: Double
//)