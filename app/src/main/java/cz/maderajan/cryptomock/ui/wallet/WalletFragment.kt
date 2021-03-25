package cz.maderajan.cryptomock.ui.wallet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cz.maderajan.cryptomock.R
import cz.maderajan.cryptomock.data.WalletUnit
import cz.maderajan.cryptomock.databinding.FragmentWalletBinding
import cz.maderajan.cryptomock.domain.WalletProvider

class WalletFragment : Fragment(R.layout.fragment_wallet) {

    private lateinit var binding: FragmentWalletBinding

    private val walletProvider: WalletProvider by lazy {
        WalletProvider(requireContext())
    }

    private val adapter: WalletAdapter by lazy {
        WalletAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWalletBinding.bind(view)

        initPortfolio()
    }

    private fun initPortfolio() {
        binding.walletRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.walletRecyclerView.adapter = adapter
        updateWalletList()
    }

    private fun updateWalletList() {
        val wallet = walletProvider.getWallet().map { entry ->
            WalletUnit(currency = entry.key, amount = entry.value)
        }
        adapter.submitList(wallet)
    }

}