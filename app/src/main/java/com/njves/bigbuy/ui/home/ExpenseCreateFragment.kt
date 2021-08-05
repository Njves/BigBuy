package com.njves.bigbuy.ui.home

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Button
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.njves.bigbuy.R
import com.njves.bigbuy.model.Expense
import com.njves.bigbuy.model.Source

class ExpenseCreateFragment : Fragment() {
    private lateinit var expenseCreateViewModel: ExpenseCreateViewModel
    private lateinit var edAmount: TextInputEditText
    private lateinit var switchIncome: SwitchMaterial
    private lateinit var edSource: TextInputEditText
    private lateinit var btnSubmit: Button

    private var income: Boolean = false
    private var amount: Float = 0f
    private var source: Source? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        expenseCreateViewModel = ViewModelProviders.of(this).get(ExpenseCreateViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_create, container, false)

        edAmount = root.findViewById(R.id.edAmount)
        switchIncome = root.findViewById(R.id.switchIncome)
        edSource = root.findViewById(R.id.edSource)
        btnSubmit = root.findViewById(R.id.btnSubmit)

        switchIncome.setOnCheckedChangeListener { buttonView, isChecked ->
            income = isChecked
        }

        btnSubmit.setOnClickListener {
            val textAmount = edAmount.text.toString()
            val textSource = edSource.text.toString()
            // Проверяем валидность строк
            // Если не прошли обрубаем код
            if (!isValidInputData(textAmount, textSource)) {
                return@setOnClickListener
            }

            amount = textAmount.toFloat()
            source = Source(textSource)
            val expense = Expense(income, source!!, amount)
            findNavController().popBackStack()

        }

        return root
    }

    private fun isValidInputData(amount: String, source: String): Boolean {
        if (TextUtils.isEmpty(amount)) {
            edAmount.error = "Заполните сумму операции"
            return false
        }

        if (!TextUtils.isEmpty(amount)) {
            if (amount.toFloat() <= 0) {
                edAmount.error = "Сумма операции должна быть больше 0!"
                return false
            }
        }

        if (TextUtils.isEmpty(source)) {
            edSource.error = "Заполните источник операции"
            return false
        }
        return true
    }

}