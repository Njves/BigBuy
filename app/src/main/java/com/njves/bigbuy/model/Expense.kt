package com.njves.bigbuy.model

import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

class Expense(@ColumnInfo(name = "is_income") val isIncome: Boolean,
              @ColumnInfo(name = "source") val source: Source,
              @ColumnInfo(name = "amount") val amount: Float, ) {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = UUID.randomUUID().toString()

    @ColumnInfo(name = "date")
    var date: Date = Date()
    @Ignore
    constructor(id: String, isIncome: Boolean, source: Source, amount: Float, date: Date) : this(isIncome, source, amount) {
        this.id = id
        this.date = date
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Expense

        if (isIncome != other.isIncome) return false
        if (source != other.source) return false
        if (amount != other.amount) return false
        if (id != other.id) return false
        if (date.time != other.date.time) return false
        return true
    }

    override fun hashCode(): Int {
        var result = isIncome.hashCode()
        result = 31 * result + source.hashCode()
        result = 31 * result + amount.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }

    override fun toString(): String {
        return "Expense(isIncome=$isIncome, source=$source, amount=$amount, id='$id', date=$date)"
    }


}