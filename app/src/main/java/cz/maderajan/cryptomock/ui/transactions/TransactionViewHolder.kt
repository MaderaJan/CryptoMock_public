package cz.maderajan.cryptomock.ui.transactions

import androidx.recyclerview.widget.RecyclerView
import cz.maderajan.cryptomock.R
import cz.maderajan.cryptomock.database.TransactionEntity
import cz.maderajan.cryptomock.databinding.ItemTranscationBinding
import cz.maderajan.cryptomock.util.presentableDate
import java.text.DecimalFormat

class TransactionViewHolder(private val binding: ItemTranscationBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(transactionEntity: TransactionEntity) {
        binding.fromToTextCurrencyTextView.text =
            itemView.context.getString(R.string.from_to_currency_with_bullet, transactionEntity.fromCurrency, transactionEntity.toCurrency)

        binding.dateTextView.text = transactionEntity.timeMillis.presentableDate()

        val toCurrencyAmount = DecimalFormat().format(transactionEntity.toCurrencyAmount)
        binding.boughtCurrencyTextView.text =
            itemView.context.getString(R.string.amount_with_currency, toCurrencyAmount, transactionEntity.toCurrency)
        val fromCurrencyAmount = DecimalFormat().format(transactionEntity.fromCurrencyAmount)
        binding.soldCurrencyTextView.text =
            itemView.context.getString(R.string.amount_with_currency, fromCurrencyAmount, transactionEntity.fromCurrency)
    }
}