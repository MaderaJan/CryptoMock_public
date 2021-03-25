package cz.maderajan.cryptomock.util

import android.content.Context
import androidx.core.content.edit
import cz.maderajan.cryptomock.BuildConfig

class PrefManager(context: Context) {

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "crypto_mock_prefs"
        private const val PREF_KEY_WALLET_BALANCE = "PREF_KEY_WALLET_BALANCE"
    }

    var wallet: String
        get() = prefs.getString(PREF_KEY_WALLET_BALANCE, BuildConfig.INITIAL_WALLET) ?: BuildConfig.INITIAL_WALLET
        set(value) = prefs.edit {
            putString(PREF_KEY_WALLET_BALANCE, value)
        }
}