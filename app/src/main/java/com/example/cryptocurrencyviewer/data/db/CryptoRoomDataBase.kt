package com.example.cryptocurrencyviewer.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CryptoEntity::class], version = 1)
abstract class CryptoRoomDataBase : RoomDatabase() {

    abstract fun cryptoDao(): DaoCryptoItem

    companion object {
        @Volatile
        private var INSTANCE: CryptoRoomDataBase? = null

        fun getDatabase(context: Context): CryptoRoomDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CryptoRoomDataBase::class.java,
                    "cryptoListTable"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
