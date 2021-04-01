package cz.maderajan.cryptomock.domain

import cz.maderajan.cryptomock.BuildConfig
import cz.maderajan.cryptomock.R
import cz.maderajan.cryptomock.data.WalletUnit
import cz.maderajan.cryptomock.repository.CoinbaseRepository
import java.math.BigDecimal

class WalletBalanceCalculator(private val coinbaseRepository: CoinbaseRepository = CoinbaseRepository()) {

    fun getWalletBalance(wallet: Map<String, BigDecimal>, successCallback: (Double) -> Unit, failureCallback: (Int) -> Unit) {
        coinbaseRepository.getExchangeRatesForCurrency(
            currency = BuildConfig.DEFAULT_CURRENCY,
            successCallback = {
                try {
                    successCallback(calculateBalance(wallet, it))
                } catch (ex: Exception) {
                    failureCallback(R.string.error_unknown)
                }
            },
            failureCallback = {
                failureCallback(R.string.error_unknown)
            }
        )
    }

    private fun calculateBalance(wallet: Map<String, BigDecimal>, exchangeRates: List<WalletUnit>): Double {
        var finalBalance = BigDecimal(0)

        wallet.forEach { entry ->
            val rate = exchangeRates.first { it.currency == entry.key }.amount
            finalBalance += entry.value / rate
        }

        return finalBalance.toDouble()
    }
}