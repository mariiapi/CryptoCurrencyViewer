package com.example.cryptocurrencyviewer.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cryptocurrencyviewer.R
import com.example.cryptocurrencyviewer.databinding.DetailsFragmentBinding
import com.example.cryptocurrencyviewer.domain.CryptoItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    // Shared ViewModel for communication
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedCryptoItem.observe(viewLifecycleOwner) { cryptoItem ->
            cryptoItem?.let {
                bindCryptoDetails(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindCryptoDetails(cryptoItem: CryptoItem) {
        // Use ViewBinding to access and update views
        binding.coinTitle.text = cryptoItem.name
        binding.coinPrice.text = cryptoItem.exchangeRate.toString()
        binding.min24Hours.text = cryptoItem.min24h.toString()
        binding.max24Hours.text = cryptoItem.max24h.toString()
        binding.lastUpdate.text = cryptoItem.lastUpdate
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks
    }
}