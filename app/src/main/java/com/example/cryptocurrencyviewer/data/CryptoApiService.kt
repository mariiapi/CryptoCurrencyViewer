package com.example.cryptocurrencyviewer.data

import retrofit2.http.GET

interface CryptoApiService {
    // https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD
    @GET("data/pricemulti?fsyms=BTC,ETH,XRP&tsyms=USD")
    suspend fun getCryptoPrices(): Map<String, Map<String, Double>>
}
