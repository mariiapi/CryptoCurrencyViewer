package com.example.cryptocurrencyviewer.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cryptocurrencyviewer.data.CryptoRepositoryImpl
import com.example.cryptocurrencyviewer.data.RetrofitClient
import com.example.cryptocurrencyviewer.domain.CryptoItem
import com.example.cryptocurrencyviewer.domain.usecases.GetCryptoPricesUseCase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val apiService = RetrofitClient.instance
    private val repository = CryptoRepositoryImpl(apiService)
    private val useCase = GetCryptoPricesUseCase(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            try {
                Log.d(TAG, "Loading...")
                val result = useCase()
                result.fold(
                    onSuccess = { data ->
                        Log.d(TAG, "Success:")
                        logCryptoData(data)
                    },
                    onFailure = { exception ->
                        Log.e(TAG, "Error: ${exception.message}")
                    }
                )
            } catch (e: Exception) {
                Log.e(TAG, "Error: ${e.message}")
            }
        }
    }

    private fun logCryptoData(data: List<CryptoItem>) {
        data.forEach {
            Log.d(
                TAG, """
                Name: ${it.name}
                Exchange Rate: USD ${it.exchangeRate}
                Last Update: ${it.lastUpdate}
                24h Min: USD ${it.min24h ?: "N/A"}
                24h Max: USD ${it.max24h ?: "N/A"}
                """.trimIndent()
            )
        }
    }

    companion object {
        const val TAG = "XXXX"
    }
}