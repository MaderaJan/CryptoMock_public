package cz.maderajan.cryptomock.domain

import android.content.Context
import cz.maderajan.cryptomock.R
import cz.maderajan.cryptomock.data.WalletUnit
import cz.maderajan.cryptomock.repository.CoinbaseRepository
import cz.maderajan.cryptomock.repository.TransactionRepository
import java.math.BigDecimal

class ExchangePoint(
    context: Context,
    private val coinbaseRepository: CoinbaseRepository = CoinbaseRepository(),
    private val transactionRepository: TransactionRepository = TransactionRepository(context)
) {

    private val walletProvider = WalletProvider(context)
    private val wallet: Map<String, BigDecimal> by lazy {
        walletProvider.getWallet()
    }

    var fromCurrency: String? = null
    var toCurrency: String? = null

    fun exchange(amountToExchangeString: String, successCallback: () -> Unit, failureCallback: (Int) -> Unit) {
        try {
            val walletBalance = wallet[fromCurrency]
            val amountToExchange = amountToExchangeString.toDouble().toBigDecimal()

            when {
                fromCurrency.isNullOrEmpty() -> failureCallback(R.string.exchange_error_from_currency_not_selected)
                toCurrency.isNullOrEmpty() -> failureCallback(R.string.exchange_error_to_currency_not_selected)
                walletBalance == null -> failureCallback(R.string.exchange_error_not_enough_money)
                walletBalance < amountToExchange -> failureCallback(R.string.exchange_error_not_enough_money)
                else -> makeExchange(amountToExchange, fromCurrency!!, toCurrency!!, successCallback, failureCallback)
            }
        } catch (ex: NumberFormatException) {
            failureCallback(R.string.exchange_error_wrong_money_format)
        }
    }

    private fun makeExchange(
        amountToExchange: BigDecimal,
        fromCurrency: String,
        toCurrency: String,
        successCallback: () -> Unit,
        failureCallback: (Int) -> Unit
    ) {
        coinbaseRepository.getExchangeRatesForCurrency(fromCurrency, successCallback = { exchangeInfoList ->
            val exchangeInfo = exchangeInfoList.firstOrNull { it.currency == toCurrency }
            if (exchangeInfo == null) {
                failureCallback(R.string.exchange_error_cannot_exchange)
            } else {
                val boughtAmount = amountToExchange * exchangeInfo.amount
                val boughtCurrency = WalletUnit(boughtAmount, toCurrency)
                val soldCurrency = WalletUnit(amountToExchange, fromCurrency)

                walletProvider.updateWallet(boughtCurrency, soldCurrency)
                transactionRepository.saveTransaction(soldCurrency, boughtCurrency)
                successCallback()
            }
        }, failureCallback = {
            failureCallback(R.string.error_unknown)
        })
    }
}