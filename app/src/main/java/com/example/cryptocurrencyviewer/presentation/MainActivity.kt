package com.example.cryptocurrencyviewer.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrencyviewer.data.CryptoApiService
import com.example.cryptocurrencyviewer.data.CryptoRepositoryImpl
import com.example.cryptocurrencyviewer.databinding.ActivityMainBinding
import com.example.cryptocurrencyviewer.domain.GetCryptoPricesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            runMainLogic()
        }

    }

    private suspend fun runMainLogic(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://min-api.cryptocompare.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(CryptoApiService::class.java)
        val repository = CryptoRepositoryImpl(apiService)
        val useCase = GetCryptoPricesUseCase(repository)
        val cryptoPrices = useCase()
        Log.d(TAG, "runMainLogic: $cryptoPrices")
    }

    companion object{
        const val TAG = "XXX"
    }
}