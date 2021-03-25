package cz.maderajan.cryptomock.ui.wallet

import androidx.recyclerview.widget.RecyclerView
import cz.maderajan.cryptomock.data.WalletUnit
import cz.maderajan.cryptomock.databinding.ItemWalletBinding
import java.text.DecimalFormat

class WalletViewHolder(private val binding: ItemWalletBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: WalletUnit) {
        binding.avatarTextView.text = item.currency.first().toString()
        binding.currencyNameTextView.text = item.currency
        binding.amountTextView.text = DecimalFormat().format(item.amount.toDouble())
    }
}