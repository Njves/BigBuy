package com.njves.bigbuy.model

interface ExpenseStorage {
    fun getExpenseList(): MutableList<Expense>
}