package com.example.cryptocurrencyviewer.data.db
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao // data access object
interface DaoCryptoItem {
    @Query("SELECT * FROM cryptoListTable")
    suspend fun getTopCryptos(): List<CryptoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cryptoEntities: List<CryptoEntity>)

    @Query("DELETE FROM cryptoListTable")
    suspend fun clearAllCryptos()
}