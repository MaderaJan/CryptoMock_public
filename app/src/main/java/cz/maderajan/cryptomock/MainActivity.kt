package cz.maderajan.cryptomock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.maderajan.cryptomock.databinding.ActivityMainBinding
import cz.maderajan.cryptomock.ui.WalletFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, WalletFragment())
            .commit()
    }
}