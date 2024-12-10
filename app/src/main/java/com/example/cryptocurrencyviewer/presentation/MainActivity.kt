package com.example.cryptocurrencyviewer.presentation

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrencyviewer.R
import com.example.cryptocurrencyviewer.databinding.ActivityMainBinding
import com.example.cryptocurrencyviewer.domain.CryptoItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnCryptoItemClickListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

//        viewModel.state.observe(this) { state ->
//            when (state) {
//                is MainViewModel.State.Loading -> {
//                    Log.d(TAG, "Loading...")
//                }
//
//                is MainViewModel.State.Success -> {
//                    Log.d(TAG, "Success:")
//                    state.data.forEach {
//                        Log.d(
//                            TAG, """
//                            Name: ${it.name}
//                            Exchange Rate: USD ${it.exchangeRate}
//                            Last Update: ${it.lastUpdate}
//                            24h Min: USD ${it.min24h ?: "N/A"}
//                            24h Max: USD ${it.max24h ?: "N/A"}
//                            """.trimIndent()
//                        )
//                    }
//                }
//
//                is MainViewModel.State.Error -> {
//                    Log.e(TAG, "Error: ${state.message}")
//                }
//            }
//        }


    }

    override fun onClick(cryptoItem: CryptoItem) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, DetailsFragment())
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment1, DetailsFragment())
                .commit()
        }
    }

    companion object {
        const val TAG = "XXXX"
    }
}