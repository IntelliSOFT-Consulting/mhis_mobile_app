package com.intellisoftkenya.android.mhis.ui.components.form

import android.content.Context
import com.google.android.material.checkbox.MaterialCheckBox

class Checkbox (context: Context, label: String, checked: Boolean) {
    private val checkBox: MaterialCheckBox = MaterialCheckBox(context)

    init {
        checkBox.text = label;
        checkBox.isChecked = checked
    }

    fun getComponent(): MaterialCheckBox {
        return checkBox
    }
}