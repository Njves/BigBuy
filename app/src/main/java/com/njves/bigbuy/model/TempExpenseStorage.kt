package com.njves.bigbuy.model

import java.util.*

class TempExpenseStorage : ExpenseStorage {
    override fun getExpenseList(): MutableList<Expense> {
        val random = Random()
        val rndItemsCount = random.nextInt(30)

        val listSource = mutableListOf("Магазин", "Банк", "Работа", "Автосервис")
        val expensesList = mutableListOf<Expense>()
        for(i in 0..rndItemsCount) {
            var rndAmount = random.nextInt(10000)
            val expense = Expense(
                random.nextBoolean(), Source(
                    listSource[random.nextInt(listSource.size)]), rndAmount.toFloat())
            expensesList.add(expense)
        }
        return expensesList
    }
}