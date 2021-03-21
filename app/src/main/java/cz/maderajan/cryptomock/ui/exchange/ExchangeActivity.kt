package cz.maderajan.cryptomock.ui.exchange

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.maderajan.cryptomock.databinding.ActivityExchangeBinding

// TODO 2 -> newIntent
// TODO 2 -> manifest
class ExchangeActivity : AppCompatActivity() {

    private val binding: ActivityExchangeBinding by lazy {
        ActivityExchangeBinding.inflate(layoutInflater)
    }

    companion object {
        private const val EXTRA_CURRENCY = "extra_currency"

        fun newIntent(context: Context): Intent = Intent(context, ExchangeActivity::class.java)

        fun newIntent(context: Context, currencyName: String): Intent = Intent(context, ExchangeActivity::class.java).apply {
            putExtra(EXTRA_CURRENCY, currencyName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
