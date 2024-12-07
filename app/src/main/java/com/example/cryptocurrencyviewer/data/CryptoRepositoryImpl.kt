package com.example.cryptocurrencyviewer.data

import com.example.cryptocurrencyviewer.domain.CryptoItem
import com.example.cryptocurrencyviewer.domain.CryptoRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(private val apiService: CryptoApiService) : CryptoRepository {
    override suspend fun getTopCryptos(): Result<List<CryptoItem>> {
        return try {
            val response = apiService.getTopCryptos()
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun CryptoResponse.toDomain(): List<CryptoItem> {
        return data.map { cryptoData ->
            CryptoItem(
                name = cryptoData.coinInfo.fullName,
                exchangeRate = cryptoData.display.usd.price.removePrefix("$").trim().replace(",", "").toDoubleOrNull() ?: 0.0,
                lastUpdate = formatTimestamp(cryptoData.display.usd.lastUpdate),
                min24h = parseToDoubleOrNull(cryptoData.display.usd.low24h),
                max24h = parseToDoubleOrNull(cryptoData.display.usd.high24h)
            )
        }
    }

    private fun parseToDoubleOrNull(value: String): Double? {
        return value.removePrefix("$").trim().replace(",", "").toDoubleOrNull()
    }

    private fun formatTimestamp(timestamp: Long): String {
        return try {
            val date = Date(timestamp * 1000L)
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            format.format(date)
        } catch (e: Exception) {
            "Invalid Date"
        }
    }
}