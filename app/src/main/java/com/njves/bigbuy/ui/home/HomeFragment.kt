package com.njves.bigbuy.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.njves.bigbuy.R
import com.njves.bigbuy.model.Expense
import com.njves.bigbuy.model.Source

class HomeFragment : Fragment(), ExpensesAdapter.ExpenseAdapterEvent {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var tvProgress: TextView
    private lateinit var pbTarget: ProgressBar
    private lateinit var rvExpenses: RecyclerView
    // FAB для добавления элементов
    private lateinit var fabAdd: FloatingActionButton
    private var adapter: ExpensesAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        fabAdd = root.findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener {

            findNavController().navigate(R.id.action_navigation_home_to_navigation_create, null, NavOptions.Builder()
                .setEnterAnim(R.anim.nav_default_enter_anim)
                .setExitAnim(R.anim.nav_default_exit_anim).build())
        }

        pbTarget = root.findViewById(R.id.pbTarget)
        tvProgress = root.findViewById(R.id.tvProgress)

        rvExpenses = root.findViewById(R.id.rvExpenses)
        rvExpenses.layoutManager = LinearLayoutManager(requireContext())
        // Инициализируем тестовый список расходов
        homeViewModel.initTestExpense()
        // Инициализируем адаптер с пустым списком
        initAdapter(mutableListOf())
        homeViewModel.expenses.observe( viewLifecycleOwner, Observer { it ->
            Log.d("Expense", it.toString())
            it.sortBy {
                it.date.time
            }
            // При обновление спискаоб обновляем адаптер
            updateAdapter(it)

            // Берем список считаем сумму расходов
            val sumExpense = sumExpense(it)
            // Записываем в прогресс бар
            updateProgressBar(sumExpense)


            // TODO Доделать эту поеботу
            /* val expensesDiffUtilCallback = ExpensesDiffUtilCallback(adapter?.getCurrentDataList() ?: listOf(), it)
            val result = DiffUtil.calculateDiff(expensesDiffUtilCallback)
            result.dispatchUpdatesTo(adapter!!) */
        })

        return root
    }
    // Инициализируем адаптер
    private fun initAdapter(dataset: MutableList<Expense>) {
        adapter = ExpensesAdapter(requireContext(), dataset, this)
        rvExpenses.adapter = adapter
    }
    // Обновляем адаптер с списком
    private fun updateAdapter(list: MutableList<Expense>) {
        adapter?.setCurrentDataList(list)
        adapter?.notifyDataSetChanged()
    }

    private fun updateProgressBar(sumExpense: Int) {

        tvProgress.text = getString(R.string.progress, sumExpense.toString(), (sumExpense + 10000).toString())
        // Иконка прогресса считаем проценты
        pbTarget.progress = calculateProgress(sumExpense, sumExpense + 10000)
    }
    // Считаем сумму расходов
    private fun sumExpense(list: List<Expense>): Int {
        var sum = 0
        list.forEach {
            sum += it.amount.toInt()
        }
        return sum
    }

    // Переводит текущую сумму накоплений в проценты
    private fun calculateProgress(currentProgress: Int, max: Int): Int {
        return (currentProgress / (max / 100))
    }


    override fun onRemoveElement(position: Int) {
        homeViewModel.removeExpense(position)
        adapter?.notifyItemRemoved(position)
    }

}