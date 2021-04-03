package cz.maderajan.cryptomock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cz.maderajan.cryptomock.databinding.ActivityMainBinding
import cz.maderajan.cryptomock.ui.exchangerate.ExchangeRatesFragment
import cz.maderajan.cryptomock.ui.transactions.TransactionsFragment
import cz.maderajan.cryptomock.ui.wallet.WalletFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_wallet -> {
                    switchFragment(WalletFragment())
                    true
                }
                R.id.action_exchange_rate -> {
                    switchFragment(ExchangeRatesFragment())
                    true
                }
                R.id.action_transactions -> {
                    switchFragment(TransactionsFragment())
                    true
                }
                else -> false
            }
        }

        switchFragment(WalletFragment())
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}