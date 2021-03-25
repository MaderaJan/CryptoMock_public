package cz.maderajan.cryptomock.domain

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cz.maderajan.cryptomock.data.WalletUnit
import cz.maderajan.cryptomock.util.PrefManager
import java.math.BigDecimal

class WalletProvider(context: Context) {

    private val prefManager = PrefManager(context)
    private val walletType = object : TypeToken<List<WalletUnit>>() {}.type

    fun getWallet(): Map<String, BigDecimal> {
        val walletBalanceJson = prefManager.wallet
        val walletList = parseWalletJson(walletBalanceJson)

        return walletList
            .map { walletUnit ->
                walletUnit.currency to walletUnit.amount
            }
            .toMap()
    }

    private fun parseWalletJson(walletBalanceJson: String): List<WalletUnit> =
        Gson().fromJson(walletBalanceJson, walletType)
}