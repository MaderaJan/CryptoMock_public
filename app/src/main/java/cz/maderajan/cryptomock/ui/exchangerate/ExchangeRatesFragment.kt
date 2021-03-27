package cz.maderajan.cryptomock.ui.exchangerate

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cz.maderajan.cryptomock.BuildConfig
import cz.maderajan.cryptomock.R
import cz.maderajan.cryptomock.data.WalletUnit
import cz.maderajan.cryptomock.databinding.FragmentExchangeBinding
import cz.maderajan.cryptomock.repository.CoinbaseRepository
import cz.maderajan.cryptomock.ui.exchange.ExchangeActivity
import cz.maderajan.cryptomock.util.toast

class ExchangeRatesFragment : Fragment(R.layout.fragment_exchange) {

    private val coinbaseRepository = CoinbaseRepository()

    // TODO 5. Vytvoření cache pro dialog
    private var cachedCurrencies = emptyList<String>()

    private lateinit var binding: FragmentExchangeBinding

    private val ratesAdapter: ExchangeRatesAdapter by lazy {
        ExchangeRatesAdapter { currencyName ->
            val intent = ExchangeActivity.newIntent(requireContext(), currencyName)
            startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExchangeBinding.bind(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = ratesAdapter

        // TODO 6. Otevření dialogu
        binding.currencyButton.setOnClickListener {
            openSelectCurrencyDialog()
        }

        // TODO 7. deleted -> ratesAdapter.submitList(coinbaseRepository.getMockedData())
        updateExchangeRates(BuildConfig.DEFAULT_CURRENCY)
    }

    // TODO 7. Call na API s currencies
    private fun updateExchangeRates(currency: String) {
        binding.currencyButton.text = currency
        coinbaseRepository.getExchangeRatesForCurrency(currency = currency,
            successCallback = { exchangeInfo ->
                cacheCurrencies(exchangeInfo)
                ratesAdapter.submitList(exchangeInfo)
            }, failureCallback = {
                context?.toast(R.string.error_unknown)
            })
    }

    // TODO 6. Otevření dialogu
    private fun openSelectCurrencyDialog() {
        AlertDialog.Builder(requireContext())
            .setSingleChoiceItems(cachedCurrencies.toTypedArray(), 0, null)
            .setPositiveButton(R.string.general_select) { dialog, _ ->
                val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                val currency = cachedCurrencies[selectedPosition]
                updateExchangeRates(currency)
            }
            .show()
    }

    private fun cacheCurrencies(exchangeInfoList: List<WalletUnit>) {
        if (cachedCurrencies.isEmpty()) {
            cachedCurrencies = exchangeInfoList.map(WalletUnit::currency)
        }
    }
}
