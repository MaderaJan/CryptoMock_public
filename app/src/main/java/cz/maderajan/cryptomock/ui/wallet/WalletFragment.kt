package cz.maderajan.cryptomock.ui.wallet

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cz.maderajan.cryptomock.BuildConfig
import cz.maderajan.cryptomock.R
import cz.maderajan.cryptomock.data.WalletUnit
import cz.maderajan.cryptomock.databinding.FragmentWalletBinding
import cz.maderajan.cryptomock.domain.WalletBalanceCalculator
import cz.maderajan.cryptomock.domain.WalletProvider
import cz.maderajan.cryptomock.ui.exchange.ExchangeActivity
import cz.maderajan.cryptomock.util.toast
import java.text.DecimalFormat

class WalletFragment : Fragment(R.layout.fragment_wallet) {

    private lateinit var binding: FragmentWalletBinding

    private val balanceCalculator: WalletBalanceCalculator = WalletBalanceCalculator()
    private val walletProvider: WalletProvider by lazy {
        WalletProvider(requireContext())
    }

    private val adapter: WalletAdapter by lazy {
        WalletAdapter()
    }

    // TODO 15. resultLauncher
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            updateWalletList()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWalletBinding.bind(view)

        // TODO 15. resultLauncher
        binding.exchangeButton.setOnClickListener {
            val intent = ExchangeActivity.newIntent(requireContext())
            resultLauncher.launch(intent)
        }

        initPortfolio()
        initBalance()
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

    // TODO 16. init ballance
    private fun initBalance() {
        balanceCalculator.getWalletBalance(walletProvider.getWallet(),
            successCallback = { balance ->
                val formattedBalance = DecimalFormat().format(balance)
                val balanceText = "$formattedBalance ${BuildConfig.DEFAULT_CURRENCY}"
                binding.balanceTextView.text = balanceText
            }, failureCallback = {
                context?.toast(it)
            })
    }
}