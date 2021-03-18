package cz.maderajan.cryptomock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cz.maderajan.cryptomock.databinding.ActivityMainBinding
import cz.maderajan.cryptomock.ui.wallet.WalletFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            TODO("TODO 2 (S) switchFragment depend on Id")
            // TODO HINT R.id.???
//            when (menuItem.itemId) {
//
//            }
        }

        switchFragment(WalletFragment())
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}