package com.example.cryptocurrencyviewer.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyviewer.R
import com.example.cryptocurrencyviewer.domain.CryptoItem


class RVFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recycler_view_fragment, container, false)

        val lst = mutableListOf(
            CryptoItem("Bitcoin", 48500.0, "2024-12-08 14:00", 47000.0, 49000.0),
            CryptoItem("Ripple", 1.2, "2024-12-08 14:00", 1.1, 1.3),
            CryptoItem("Ethereum", 3200.0, "2024-12-08 14:00", 3100.0, 3300.0),
        )

        recyclerView = view.findViewById(R.id.rvCryptoList)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val listener = activity as OnCryptoItemClickListener
        recyclerView.adapter = CryptoAdapter(lst, listener)

        return view
    }

}