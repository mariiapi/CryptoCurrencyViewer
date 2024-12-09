package com.example.cryptocurrencyviewer.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptocurrencyviewer.domain.CryptoItem

// creates a twin of a CryptoItem for domain to be independent from data layer
@Entity(tableName = "cryptoListTable")
data class CryptoEntity (

    @PrimaryKey // unique identifier in db
    @ColumnInfo("name_item")
    val name: String,
    @ColumnInfo("exchangeRate_item")
    val exchangeRate: Double,
    @ColumnInfo("lastUpdate_item")
    val lastUpdate: String,
    @ColumnInfo("min24h_item")
    val min24h: Double?,
    @ColumnInfo("max24h_item")
    val max24h: Double?

) {
    fun toCryptoItem() : CryptoItem {
        return CryptoItem(
            name = name,
            exchangeRate = exchangeRate,
            lastUpdate = lastUpdate,
            min24h = min24h,
            max24h = max24h
        )
    }
}