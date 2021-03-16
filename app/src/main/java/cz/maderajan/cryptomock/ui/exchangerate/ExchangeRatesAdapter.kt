package cz.maderajan.cryptomock.ui.exchangerate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cz.maderajan.cryptomock.data.WalletUnit
import cz.maderajan.cryptomock.databinding.ItemExchangeRatesBinding

// TODO 5. Adpater
class ExchangeRatesAdapter(private val currencySelectedCallback: (String) -> Unit) : RecyclerView.Adapter<ExchangeRatesViewHolder>() {

    private var items: List<WalletUnit> = emptyList()

    // TODO 5. onCreateViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeRatesViewHolder =
        ExchangeRatesViewHolder(ItemExchangeRatesBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    // TODO 5. onBindViewHolder
    override fun onBindViewHolder(holderRates: ExchangeRatesViewHolder, position: Int) {
        holderRates.bind(items[position], currencySelectedCallback)
    }

    override fun getItemCount(): Int = items.size

    // TODO 5. submitList
    fun submitList(newItems: List<WalletUnit>) {
        items = newItems

        // TODO 5. notifyDataSetChanged
        notifyDataSetChanged()
    }
}

