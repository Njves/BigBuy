package com.njves.bigbuy.model

import java.util.*

data class Expense(val id: UUID ,val isIncome: Boolean, val source: Source, val amount: Float, val isNegative: Boolean)