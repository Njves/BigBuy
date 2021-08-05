package com.njves.bigbuy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.njves.bigbuy.model.Expense
import com.njves.bigbuy.model.ExpenseStorage
import com.njves.bigbuy.model.Source
import com.njves.bigbuy.model.TempExpenseStorage
import java.util.*

class HomeViewModel : ViewModel() {
    var expenses: MutableLiveData<MutableList<Expense>> = MutableLiveData()
    var expenseStorage: ExpenseStorage? = null


    fun initTestExpense() {
        expenseStorage = TempExpenseStorage()
        val list = expenseStorage?.getExpenseList()
        expenses.postValue(list)
    }


    fun removeExpense(position: Int) {
        val list = expenses.value
        list?.removeAt(position)
        expenses.postValue(list)
    }

    fun addExpense(position: Int, expense: Expense) {
        val list = expenses.value
        list?.add(position, expense)
        expenses.postValue(list)
    }

    fun addExpense(expense: Expense) {
        val list = expenses.value
        list?.add(expense)
        expenses.postValue(list)
    }





}