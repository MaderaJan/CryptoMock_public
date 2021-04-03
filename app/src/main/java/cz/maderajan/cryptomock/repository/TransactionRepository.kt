package cz.maderajan.cryptomock.repository

import android.content.Context
import cz.maderajan.cryptomock.data.WalletUnit
import cz.maderajan.cryptomock.database.CryptoMockDatabase
import cz.maderajan.cryptomock.database.TransactionDao
import cz.maderajan.cryptomock.database.TransactionEntity

class TransactionRepository(
    context: Context,
    private val transactionDao: TransactionDao = CryptoMockDatabase.create(context).transactionDao()
) {

    fun saveTransaction(fromCurrency: WalletUnit, toCurrency: WalletUnit) {
        val transactionEntity = TransactionEntity(
            fromCurrency = fromCurrency.currency,
            fromCurrencyAmount = fromCurrency.amount.toDouble(),
            toCurrency = toCurrency.currency,
            toCurrencyAmount = toCurrency.amount.toDouble(),
            timeMillis = System.currentTimeMillis()
        )

        transactionDao.persist(transactionEntity)
    }

    fun getAllTransactions(): List<TransactionEntity> =
        transactionDao.getAllTransactions()
}