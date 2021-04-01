package cz.maderajan.cryptomock.webservice

import cz.maderajan.cryptomock.webservice.response.ExchangeRatesWrapperResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinbaseApi {

    @GET("exchange-rates")
    fun getExchangeRatesForCurrency(
        @Query(value = "currency") currency: String
    ): Call<ExchangeRatesWrapperResponse>

    @GET("exchange-rates")
    fun getExchangeRatesForCurrency(): Call<ExchangeRatesWrapperResponse>
}