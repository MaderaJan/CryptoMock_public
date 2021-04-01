package cz.maderajan.cryptomock.repository

import cz.maderajan.cryptomock.data.WalletUnit
import cz.maderajan.cryptomock.webservice.CoinbaseApi
import cz.maderajan.cryptomock.webservice.RetrofitUtil
import cz.maderajan.cryptomock.webservice.response.ExchangeRatesWrapperResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigDecimal

class CoinbaseRepository(private val coinbaseApi: CoinbaseApi = RetrofitUtil.createCoinbaseApi()) {

    fun getExchangeRatesForCurrency(currency: String, successCallback: (List<WalletUnit>) -> Unit, failureCallback: () -> Unit) {
        coinbaseApi.getExchangeRatesForCurrency(currency)
            .enqueue(object : Callback<ExchangeRatesWrapperResponse> {

                override fun onResponse(call: Call<ExchangeRatesWrapperResponse>, response: Response<ExchangeRatesWrapperResponse>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        val exchangeInfoList = responseBody.mapToExchangeInfo()
                        successCallback(exchangeInfoList)
                    } else {
                        failureCallback()
                    }
                }

                override fun onFailure(call: Call<ExchangeRatesWrapperResponse>, t: Throwable) {
                    failureCallback()
                }
            })
    }

    private fun ExchangeRatesWrapperResponse?.mapToExchangeInfo(): List<WalletUnit> =
        this?.data?.rates?.map { (currency, amount) ->
            WalletUnit(currency = currency, amount = BigDecimal.valueOf(amount))
        } ?: emptyList()

    fun getAvailableCurrencies(successCallback: (List<String>) -> Unit, failureCallback: () -> Unit) {
        coinbaseApi.getExchangeRatesForCurrency()
            .enqueue(object : Callback<ExchangeRatesWrapperResponse> {

                override fun onResponse(call: Call<ExchangeRatesWrapperResponse>, response: Response<ExchangeRatesWrapperResponse>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        val availableCurrencies = responseBody.mapToCurrency()
                        successCallback(availableCurrencies)
                    } else {
                        failureCallback()
                    }
                }

                override fun onFailure(call: Call<ExchangeRatesWrapperResponse>, t: Throwable) {
                    failureCallback()
                }
            })
    }

    private fun ExchangeRatesWrapperResponse?.mapToCurrency(): List<String> =
        this?.data?.rates?.map {
            it.key
        } ?: emptyList()
}