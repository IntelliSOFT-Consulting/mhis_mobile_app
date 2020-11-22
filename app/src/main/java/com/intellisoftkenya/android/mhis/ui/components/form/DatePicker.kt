package com.intellisoftkenya.android.mhis.ui.components.form

import android.content.Context
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class DatePicker(context: Context, label: String, fragmentManager: FragmentManager) {

    private val button: MaterialButton = MaterialButton(context)
    init {
        button.text = label
        button.setOnClickListener(View.OnClickListener {
            val datePickerBuilder: MaterialDatePicker.Builder<Long> = MaterialDatePicker.Builder.datePicker()
            datePickerBuilder.setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
            val picker: MaterialDatePicker<*> = datePickerBuilder.build()
            picker.show(fragmentManager, picker.toString())
            picker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                Log.d("mhis", "Picked $it")
                calendar.time = Date("$it".toLong())
            }
        })

    }

    fun getComponent(): MaterialButton {
        return button
    }
}