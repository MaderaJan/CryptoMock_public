package cz.maderajan.cryptomock.ui.exchangerate

import androidx.recyclerview.widget.RecyclerView
import cz.maderajan.cryptomock.data.WalletUnit
import cz.maderajan.cryptomock.databinding.ItemExchangeRatesBinding
import java.text.DecimalFormat

class ExchangeRatesViewHolder(private val binding: ItemExchangeRatesBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(exchangeInfo: WalletUnit, currencySelectedCallback: (String) -> Unit) {
        binding.amountTextView.text = DecimalFormat().format(exchangeInfo.amount.toDouble())
        binding.avatarTextView.text = exchangeInfo.currency.firstOrNull().toString()
        binding.currencyNameTextView.text = exchangeInfo.currency

        binding.container.setOnClickListener {
            currencySelectedCallback(exchangeInfo.currency)
        }
    }
}