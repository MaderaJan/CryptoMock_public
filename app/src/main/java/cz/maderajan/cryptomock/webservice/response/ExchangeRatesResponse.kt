package cz.maderajan.cryptomock.webservice.response

// TODO 1. DATA LAYER
data class ExchangeRatesWrapperResponse(
    val data: ExchangeRatesResponse
)

data class ExchangeRatesResponse(
    val currency: String,
    val rates: Map<String, Double>
)