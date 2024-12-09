package com.example.cryptocurrencyviewer.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyviewer.R
import com.example.cryptocurrencyviewer.domain.CryptoItem

interface OnCryptoItemClickListener {
    fun onClick(cryptoItem: CryptoItem)
}

class CryptoAdapter(
    private val currencies: MutableList<CryptoItem>,
    private val listener: OnCryptoItemClickListener
) :
    RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = view.findViewById<CardView>(R.id.cardView_cryptoItem)
        val currency: TextView = view.findViewById<TextView>(R.id.tvCurrency)
        val exchange: TextView = view.findViewById<TextView>(R.id.tvExchangeValue)
        val lastUpdate: TextView = view.findViewById<TextView>(R.id.tvUpdate)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.crypto_item, viewGroup, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val cryptoItem = currencies[position]

        viewHolder.currency.text = cryptoItem.name
        viewHolder.exchange.text = cryptoItem.exchangeRate.toString()
        viewHolder.lastUpdate.text = cryptoItem.lastUpdate

        viewHolder.cardView.setOnClickListener {
            listener.onClick(cryptoItem)
        }
    }

    override fun getItemCount() = currencies.size

    fun setData(newData: List<CryptoItem>) {
        currencies.clear()
        currencies.addAll(newData)
        notifyDataSetChanged()
    }
}