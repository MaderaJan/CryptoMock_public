package cz.maderajan.cryptomock.ui.exchangerate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cz.maderajan.cryptomock.R
import cz.maderajan.cryptomock.databinding.FragmentExchangeBinding
import cz.maderajan.cryptomock.util.toast

// TODO 2. Exchange fragment - basic
class ExchangeRatesFragment : Fragment(R.layout.fragment_exchange) {

    private lateinit var binding: FragmentExchangeBinding

    private val ratesAdapter: ExchangeRatesAdapter by lazy {
        ExchangeRatesAdapter { currencyName ->
            // TODO 8. Kotlin extension
            context?.toast(R.string.exchange_title)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExchangeBinding.bind(view)

        // TODO 7. Layout manager
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = ratesAdapter

        // TODO 9. (S) ratesAdapter.submitList(coinbaseRepository.getMockedData())
    }
}
