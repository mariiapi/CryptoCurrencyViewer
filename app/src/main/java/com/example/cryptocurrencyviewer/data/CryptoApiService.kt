package com.example.cryptocurrencyviewer.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// DTOs
data class CryptoResponse(
    val data: List<CryptoData>
)

data class CryptoData(
    val coinInfo: CoinInfo,
    val display: Display
)

data class CoinInfo(
    val fullName: String
)

data class Display(
    val usd: Usd
)

data class Usd(
    val price: String,
    val lastUpdate: Long, // We'll handle "Just now" in the deserializer
    val low24h: String,
    val high24h: String
)


interface CryptoApiService {
    // https://min-api.cryptocompare.com/data/top/totalvolfull?limit=30&tsym=USD
    @GET("data/top/totalvolfull")
    suspend fun getTopCryptos(
        @Query("limit") limit: Int = 30,
        @Query("tsym") tsym: String = "USD"
    ): CryptoResponse

}

object RetrofitClient {
    private const val BASE_URL = "https://min-api.cryptocompare.com/"

    val instance: CryptoApiService by lazy {
        val gson = GsonBuilder()
            .registerTypeAdapter(CryptoResponse::class.java, CryptoResponseDeserializer())
            .create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CryptoApiService::class.java)
    }
}

