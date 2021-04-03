package cz.maderajan.cryptomock.ui.transactions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cz.maderajan.cryptomock.R
import cz.maderajan.cryptomock.databinding.FragmentTranscationsBinding
import cz.maderajan.cryptomock.repository.TransactionRepository

class TransactionsFragment : Fragment(R.layout.fragment_transcations) {

    private lateinit var binding: FragmentTranscationsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTranscationsBinding.bind(view)

        binding.transactionRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val transactionRepository = TransactionRepository(requireContext())
        val transactions = transactionRepository.getAllTransactions()

        val adapter = TransactionAdapter(transactions)
        binding.transactionRecyclerView.adapter = adapter
    }
}