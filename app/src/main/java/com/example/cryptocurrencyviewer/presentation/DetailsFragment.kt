package com.example.cryptocurrencyviewer.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cryptocurrencyviewer.R
import com.example.cryptocurrencyviewer.domain.CryptoItem

class DetailsFragment : Fragment(R.layout.details_fragment) {

    companion object {
        fun newInstance(cryptoItem: CryptoItem): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putSerializable("crypto_item", cryptoItem)
            fragment.arguments = args
            return fragment
        }
    }
}