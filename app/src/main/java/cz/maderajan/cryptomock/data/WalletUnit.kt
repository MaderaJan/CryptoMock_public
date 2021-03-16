package cz.maderajan.cryptomock.data

import java.io.Serializable
import java.math.BigDecimal

// TODO 3. data class, big decimal
data class WalletUnit(
    val amount: BigDecimal,
    val currency: String
) : Serializable