package com.example.cryptocurrencyviewer.presentation

//private val items = mutableListOf<CryptoItem>(
//    CryptoItem("Bitcoin", 48500.0, "2024-12-08 14:00", 47000.0, 49000.0),
//    CryptoItem("Ethereum", 3200.0, "2024-12-08 14:00", 3100.0, 3300.0),
//    CryptoItem("Litecoin", 150.0, "2024-12-08 14:00", 145.0, 155.0),
//    CryptoItem("Dogecoin", 0.25, "2024-12-08 14:00", 0.20, 0.30),
//    CryptoItem("Ripple", 1.2, "2024-12-08 14:00", 1.1, 1.3)
//)

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyviewer.R
import com.example.cryptocurrencyviewer.domain.CryptoItem

class CryptoAdapter(
    private val currencies: MutableList<CryptoItem> = mutableListOf(
        CryptoItem("Bitcoin", 48500.0, "2024-12-08 14:00", 47000.0, 49000.0),
        CryptoItem("Batcoin", 47500.0, "2024-12-08 14:00", 47000.0, 49000.0),
    )
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

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.currency.text = currencies[position].name
        viewHolder.exchange.text = currencies[position].exchangeRate.toString()
        viewHolder.lastUpdate.text = currencies[position].lastUpdate

//        viewHolder.catImage.setImageResource(dataSet[position].resource)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = currencies.size

}