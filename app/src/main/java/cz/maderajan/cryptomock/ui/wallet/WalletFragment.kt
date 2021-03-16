package cz.maderajan.cryptomock.ui.wallet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import cz.maderajan.cryptomock.R
import cz.maderajan.cryptomock.databinding.FragmentWalletBinding

class WalletFragment : Fragment(R.layout.fragment_wallet) {

    private lateinit var binding: FragmentWalletBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWalletBinding.bind(view)
    }
}