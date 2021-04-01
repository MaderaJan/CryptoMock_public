package cz.maderajan.cryptomock.ui.exchange

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import cz.maderajan.cryptomock.R
import cz.maderajan.cryptomock.databinding.ActivityExchangeBinding
import cz.maderajan.cryptomock.domain.ExchangePoint
import cz.maderajan.cryptomock.domain.WalletProvider
import cz.maderajan.cryptomock.repository.CoinbaseRepository
import cz.maderajan.cryptomock.util.toast

class ExchangeActivity : AppCompatActivity() {

    private val coinbaseRepository = CoinbaseRepository()

    private val exchangePoint: ExchangePoint by lazy {
        ExchangePoint(applicationContext)
    }

    private val binding: ActivityExchangeBinding by lazy {
        ActivityExchangeBinding.inflate(layoutInflater)
    }

    companion object {
        private const val EXTRA_CURRENCY = "extra_currency"

        fun newIntent(context: Context): Intent = Intent(context, ExchangeActivity::class.java)

        fun newIntent(context: Context, currencyName: String): Intent =
            Intent(context, ExchangeActivity::class.java)
                .putExtra(EXTRA_CURRENCY, currencyName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        initFromCurrencySpinner()
        initToCurrencySpinner()

        // TODO 14. Exchange + backbutton
        binding.exchangeButton.setOnClickListener {
            exchangePoint.exchange(amountToExchangeString = binding.amountEditText.text.toString(),
                successCallback = {
                    toast(R.string.exchange_success_exchange)
                    setResult(Activity.RESULT_OK)
                    finish()
                }, failureCallback = {
                    toast(it)
                })
        }
    }

    // TODO 10. init currency spinner
    private fun initFromCurrencySpinner() {
        val walletCurrencies = WalletProvider(applicationContext).getWallet().map { it.key }
        val fromCurrenciesAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, walletCurrencies)
        binding.fromCurrencySpinner.adapter = fromCurrenciesAdapter
        binding.fromCurrencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val selectedCurrency = walletCurrencies[position]
                binding.selectedCurrencyTextView.text = selectedCurrency
                exchangePoint.fromCurrency = selectedCurrency
            }
        }
    }

    // TODO 11. Exchange point
    // TODO 12. (S) init currency spinner (z API)
    private fun initToCurrencySpinner() {
//                val preselectedCurrency = intent.getStringExtra(EXTRA_CURRENCY)
//                val preselectedCurrencyIndex = currencies.indexOfFirst { it == preselectedCurrency }
//                if (preselectedCurrencyIndex != -1) {
//                    binding.toCurrencySpinner.setSelection(preselectedCurrencyIndex)
//                }
    }
}
