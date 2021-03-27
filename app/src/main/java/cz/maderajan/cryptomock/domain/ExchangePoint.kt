package cz.maderajan.cryptomock.domain

import android.content.Context
import cz.maderajan.cryptomock.R
import cz.maderajan.cryptomock.repository.CoinbaseRepository
import java.math.BigDecimal

class ExchangePoint(context: Context, private val coinbaseRepository: CoinbaseRepository = CoinbaseRepository()) {

    private val walletProvider = WalletProvider(context)
    private val wallet: Map<String, BigDecimal> by lazy {
        walletProvider.getWallet()
    }

    var fromCurrency: String? = null
    var toCurrency: String? = null

    // TODO 13. popsat funkci exchange
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
        // TODO 13. (S) make exchange
        // TODO -> uložení do wallet walletProvider.updateWallet()
    }
}