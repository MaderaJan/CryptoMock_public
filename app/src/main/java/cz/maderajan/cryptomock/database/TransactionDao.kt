package cz.maderajan.cryptomock.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun persist(transactionEntity: TransactionEntity)

    @Query("SELECT * FROM TransactionEntity")
    fun getAllTransactions(): List<TransactionEntity>
}