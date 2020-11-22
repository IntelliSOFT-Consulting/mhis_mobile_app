package com.intellisoftkenya.android.mhis.ui.components.form

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import javax.annotation.Nullable


class TextInput (context: Context, label: String, @Nullable inputType: Int = 0) {
    private val input: TextInputLayout = TextInputLayout(context)

    init {
        input.hint = label
        val editTextParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        val editLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        input.layoutParams = editTextParams
        input.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
        val editText = TextInputEditText(context)
        if (inputType > 0) {
            editText.inputType = inputType
        }
        editText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }
        })
        input.addView(editText, editTextParams)
    }

    fun getComponent(): TextInputLayout {
        return input
    }
}