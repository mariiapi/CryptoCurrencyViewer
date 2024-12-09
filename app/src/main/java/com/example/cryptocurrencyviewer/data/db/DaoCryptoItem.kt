package com.example.cryptocurrencyviewer.data.db
import androidx.room.Dao
import androidx.room.Query

@Dao // data access object

interface DaoCryptoItem {
    @Query("SELECT * FROM cryptoListTable")
    suspend fun getTopCryptos(): Result<List<CryptoEntity>>
}