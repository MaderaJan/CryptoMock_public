package cz.maderajan.cryptomock.data

import java.io.Serializable
import java.math.BigDecimal

data class WalletUnit(
    val amount: BigDecimal,
    val currency: String
) : Serializable