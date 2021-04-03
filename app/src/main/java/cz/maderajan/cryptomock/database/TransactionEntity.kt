package cz.maderajan.cryptomock.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fromCurrencyAmount: Double,
    val fromCurrency: String,
    val toCurrencyAmount: Double,
    val toCurrency: String,
    val timeMillis: Long
)