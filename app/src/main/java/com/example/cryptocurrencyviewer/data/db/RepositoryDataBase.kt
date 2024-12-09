package com.example.cryptocurrencyviewer.data.db

import android.content.Context
import com.example.cryptocurrencyviewer.domain.CryptoItem
import com.example.cryptocurrencyviewer.domain.CryptoRepository
import javax.inject.Inject

class RepositoryDataBase @Inject constructor(context: Context) : CryptoRepository {

    private val dao = CryptoRoomDataBase.getDatabase(context).cryptoDao()

    override suspend fun getTopCryptos(): Result<List<CryptoItem>> {
        return try {
            val cryptoEntities = dao.getTopCryptos()
            val cryptoItems = cryptoEntities.getOrThrow().map { it.toCryptoItem() }
            Result.success(cryptoItems)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}