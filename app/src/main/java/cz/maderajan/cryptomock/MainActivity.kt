package cz.maderajan.cryptomock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.maderajan.cryptomock.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root) // TODO 3. binding view

        // TODO 7. Fragment manager
    }
}