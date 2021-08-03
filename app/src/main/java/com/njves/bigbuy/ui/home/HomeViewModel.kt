package com.njves.bigbuy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.njves.bigbuy.model.Expense
import com.njves.bigbuy.model.Source
import java.util.*

class HomeViewModel : ViewModel() {
    var expenses: MutableLiveData<MutableList<Expense>> = MutableLiveData()



    fun initTestExpense() {
        val random = Random()
        val rndItemsCount = random.nextInt(30)

        val listSource = mutableListOf("Магазин", "Банк", "Работа", "Автосервис")
        val expensesList = mutableListOf<Expense>()
        for(i in 0..rndItemsCount) {
            var rndAmount = random.nextInt(10000)
            val expense = Expense(
                random.nextBoolean(), Source(
                    listSource[random.nextInt(listSource.size)]), rndAmount.toFloat(),
                random.nextBoolean())
            expensesList.add(expense)
        }
        expenses.postValue(expensesList)
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