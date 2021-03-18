package cz.maderajan.cryptomock.ui.exchangerate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cz.maderajan.cryptomock.data.WalletUnit
import cz.maderajan.cryptomock.databinding.ItemExchangeRatesBinding

class ExchangeRatesAdapter(private val currencySelectedCallback: (String) -> Unit) : RecyclerView.Adapter<ExchangeRatesViewHolder>() {

    private var items: List<WalletUnit> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeRatesViewHolder {
        val binding = ItemExchangeRatesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExchangeRatesViewHolder(binding)
    }

    override fun onBindViewHolder(holderRates: ExchangeRatesViewHolder, position: Int) {
        val item = items[position]
        holderRates.bind(item, currencySelectedCallback)
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<WalletUnit>) {
        items = newItems
        notifyDataSetChanged()
    }
}

