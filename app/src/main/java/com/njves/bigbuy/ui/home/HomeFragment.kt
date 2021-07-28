package com.njves.bigbuy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.njves.bigbuy.R
import com.njves.bigbuy.model.Expense
import com.njves.bigbuy.model.Source
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var tvProgress: TextView
    private lateinit var pbTarget: ProgressBar
    private lateinit var rvExpenses: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        pbTarget = root.findViewById(R.id.pbTarget)
        tvProgress = root.findViewById(R.id.tvProgress)

        rvExpenses = root.findViewById(R.id.rvExpenses)
        rvExpenses.layoutManager = LinearLayoutManager(requireContext())
        rvExpenses.adapter = ExpensesAdapter(requireContext(),
            mutableListOf(Expense(UUID.randomUUID(), true, Source(UUID.randomUUID(), "Магазин"), 5000f, false),
                Expense(UUID.randomUUID(), true, Source(UUID.randomUUID(), "Магазин"), 5000f, true),
                Expense(UUID.randomUUID(), true, Source(UUID.randomUUID(), "Магазин"), 5000f, true)))
        pbTarget.progress = calculateProgress(2500, 3000)
        return root
    }
    // Переводит текущую сумму накоплений в проценты
    private fun calculateProgress(currentProgress: Int, max: Int): Int {
        return (currentProgress / (max / 100))
    }
}