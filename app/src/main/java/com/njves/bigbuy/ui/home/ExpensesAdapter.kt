package com.njves.bigbuy.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.njves.bigbuy.R
import com.njves.bigbuy.model.Expense

// Список расходов
class ExpensesAdapter(private val context: Context, private val expenses: MutableList<Expense>) : RecyclerView.Adapter<ExpensesAdapter.ExpensesViewHolder>() {
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

    class ExpensesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivIsIncome = itemView.findViewById<ImageView>(R.id.ivIsIncome)
        private val tvSource = itemView.findViewById<TextView>(R.id.tvSource)
        private val tvAmount = itemView.findViewById<TextView>(R.id.tvAmount)

        // Берем данные из списка расходов и прикрепляем к вьюшкам списка
        fun bind(expense: Expense) {
            ivIsIncome.setImageResource(getIncomeImage(expense.isNegative))
            tvSource.text = expense.source.title
            tvAmount.text = expense.amount.toString()
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

}