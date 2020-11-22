package com.intellisoftkenya.android.mhis.ui.components.form

import android.content.Context
import android.util.Log
import com.google.android.material.button.MaterialButton

class Button (context: Context, title: String) {

    private val button: MaterialButton = MaterialButton(context)
    init {
        button.text = title
        button.setOnClickListener { view ->
            Log.d("mhis", "Clicked on button")
        }
    }

    fun getComponent(): MaterialButton {
        return button
    }
}