package com.example.cryptocurrencyviewer.presentation

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
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

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, RVFragment())
                .commit()
        }
    }

    override fun onClick(cryptoItem: CryptoItem) {
        val detailsFragment = DetailsFragment.newInstance(cryptoItem)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, detailsFragment)
            .addToBackStack(null)
            .commit()
    }


    companion object {
        const val TAG = "XXXX"
    }
}