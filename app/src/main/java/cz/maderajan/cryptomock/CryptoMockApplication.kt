package cz.maderajan.cryptomock

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class CryptoMockApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}