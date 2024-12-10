package com.example.cryptocurrencyviewer.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyviewer.R
import com.example.cryptocurrencyviewer.domain.CryptoItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RVFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CryptoAdapter
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recycler_view_fragment, container, false)

        val lst = mutableListOf(
            CryptoItem("Bitcoin", 48500.0, "2024-12-08 14:00", 47000.0, 49000.0, "/media/37746251/btc.png"),
            CryptoItem("Ripple", 1.2, "2024-12-08 14:00", 1.1, 1.3, ""),
            CryptoItem("Ethereum", 3200.0, "2024-12-08 14:00", 3100.0, 3300.0, "")
        )

        recyclerView = view.findViewById(R.id.rvCryptoList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CryptoAdapter(lst, object : OnCryptoItemClickListener {
            override fun onClick(cryptoItem: CryptoItem) {
                viewModel.selectCryptoItem(cryptoItem)

                val action = RVFragmentDirections.actionRVFragmentToDetailsFragment()
                findNavController().navigate(action)
            }
        })

        recyclerView.adapter = adapter

        return view
    }
}
