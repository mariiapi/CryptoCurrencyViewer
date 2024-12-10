package com.example.cryptocurrencyviewer.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

        recyclerView = view.findViewById(R.id.rvCryptoList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CryptoAdapter(mutableListOf(), activity as OnCryptoItemClickListener)
        recyclerView.adapter = adapter

        observeViewModel()

        return view
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            val TAG = "XXXX"
            when (state) {
                is MainViewModel.State.Success -> {
                    // Update the RecyclerView with new data
                    adapter.setData(state.data)
                }

                is MainViewModel.State.Error -> Log.d(TAG, "observeViewModel: error")
                MainViewModel.State.Loading -> Log.d(TAG, "observeViewModel: loading...")
            }
        }
    }
}
