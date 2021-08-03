package com.njves.bigbuy.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.njves.bigbuy.R
import com.njves.bigbuy.model.Expense

// Список расходов
class ExpensesAdapter(private val context: Context, private var expenses: MutableList<Expense>, public val listener: ExpenseAdapterEvent) : RecyclerView.Adapter<ExpensesAdapter.ExpensesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_expense, parent, false)
        return ExpensesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        holder.bind(expenses[position])
    }

    override fun getItemCount(): Int {
        return expenses.size
    }

    fun getCurrentDataList(): MutableList<Expense> {
        return expenses
    }

    fun setCurrentDataList(list: MutableList<Expense>) {
        expenses = list
    }
    inner class ExpensesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivIsIncome = itemView.findViewById<ImageView>(R.id.ivIsIncome)
        private val tvSource = itemView.findViewById<TextView>(R.id.tvSource)
        private val tvAmount = itemView.findViewById<TextView>(R.id.tvAmount)
        private val btnRemove = itemView.findViewById<Button>(R.id.btnRemove)
        // Берем данные из списка расходов и прикрепляем к вьюшкам списка
        fun bind(expense: Expense) {
            ivIsIncome.setImageResource(getIncomeImage(expense.isNegative))
            tvSource.text = expense.source.title
            tvAmount.text = expense.amount.toString()
            btnRemove.setOnClickListener {
                listener.onRemoveElement(adapterPosition)
            }
            itemView.setOnClickListener {
                tvAmount.text = "Тык!"
            }

        }

        fun getIncomeImage(isNegative: Boolean): Int {
            if(isNegative)
                return R.drawable.ic_income_down
            return R.drawable.ic_income_up
        }
    }

    public interface ExpenseAdapterEvent {
        fun onRemoveElement(position: Int)
    }

}