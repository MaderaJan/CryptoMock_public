package cz.maderajan.cryptomock.ui.wallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cz.maderajan.cryptomock.data.WalletUnit
import cz.maderajan.cryptomock.databinding.ItemWalletBinding

class WalletAdapter(private var items: List<WalletUnit> = emptyList()) : RecyclerView.Adapter<WalletViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder =
        WalletViewHolder(ItemWalletBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<WalletUnit>) {
        this.items = newItems
        items = newItems
        notifyDataSetChanged()
    }
}