package com.example.cryptocurrencyviewer.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cryptocurrencyviewer.R
import com.example.cryptocurrencyviewer.domain.CryptoItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.details_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cryptoItem = arguments?.getSerializable("crypto_item") as CryptoItem?

        if (cryptoItem != null) {
            view.findViewById<TextView>(R.id.tvCurrency).text = cryptoItem.name
            view.findViewById<TextView>(R.id.tvExchangeValue).text = "${cryptoItem.exchangeRate}"

        }
    }
}