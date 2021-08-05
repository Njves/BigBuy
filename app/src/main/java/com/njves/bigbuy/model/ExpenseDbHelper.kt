package com.njves.bigbuy.model

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

class ExpenseDbHelper(private val context: Context, ) : SQLiteOpenHelper(context, DB_NAME, null, VERSION) {
    companion object {
        const val DB_NAME = "expense_db"
        const val VERSION = 1
    }
}

