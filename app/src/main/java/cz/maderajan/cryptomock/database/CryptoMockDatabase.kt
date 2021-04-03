package cz.maderajan.cryptomock.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TransactionEntity::class],
    version = 1
)
abstract class CryptoMockDatabase : RoomDatabase() {

    companion object {
        private const val NAME = "crypto-mock.db"

        fun create(context: Context): CryptoMockDatabase =
            Room.databaseBuilder(context, CryptoMockDatabase::class.java, NAME)
                .allowMainThreadQueries()
                .build()
    }

    abstract fun transactionDao(): TransactionDao
}