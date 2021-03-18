package cz.maderajan.cryptomock.repository

import cz.maderajan.cryptomock.data.WalletUnit
import java.math.BigDecimal

class CoinbaseRepository {

    fun getMockedData(): List<WalletUnit> {
        val list = mutableListOf<WalletUnit>()
        for (x in 1..100) {
            list.add(WalletUnit(BigDecimal(1), "curr-$x"))
        }
        return list
    }

    fun getMockedData2() =
        mutableListOf<WalletUnit>().apply {
            for (x in 1..100) {
                add(WalletUnit(BigDecimal(1), "curr-$x"))
            }
        }

    fun getMockedData3() =
        listOf(
            WalletUnit(BigDecimal(1), "curr-$"),
            WalletUnit(BigDecimal(1), "curr-$"),
            WalletUnit(BigDecimal(1), "curr-$"),
            WalletUnit(BigDecimal(1), "curr-$"),
            WalletUnit(BigDecimal(1), "curr-$"),
            WalletUnit(BigDecimal(1), "curr-$"),
            WalletUnit(BigDecimal(1), "curr-$"),
            WalletUnit(BigDecimal(1), "curr-$"),
            WalletUnit(BigDecimal(1), "curr-$"),
        )
}