package cz.maderajan.cryptomock.ui.exchangerate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cz.maderajan.cryptomock.R
import cz.maderajan.cryptomock.databinding.FragmentExchangeBinding
import cz.maderajan.cryptomock.repository.CoinbaseRepository
import cz.maderajan.cryptomock.ui.exchange.ExchangeActivity

class ExchangeRatesFragment : Fragment(R.layout.fragment_exchange) {

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

        val coinbaseRepository = CoinbaseRepository()
        ratesAdapter.submitList(coinbaseRepository.getMockedData())
    }
}
