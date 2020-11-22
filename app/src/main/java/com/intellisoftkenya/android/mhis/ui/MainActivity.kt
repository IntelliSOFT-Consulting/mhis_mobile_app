package com.intellisoftkenya.android.mhis.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.intellisoftkenya.android.mhis.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSignIn(view: View) {
        val userNameEditText = this.findViewById<EditText>(R.id.userName)
        val passWordEditText = this.findViewById<EditText>(R.id.password)

        var hasError = false;
        if (userNameEditText.text.toString() == "") {
            userNameEditText.setError(getString(R.string.requiredField))
            hasError = true
        }
        if (passWordEditText.text.toString() == "") {
            passWordEditText.setError(getString(R.string.requiredField))
            hasError = true
        }
        if (hasError) {
            return
        }
        val showMainScreen =  Intent(this, HomeActivity::class.java)
        startActivity(showMainScreen)
    }
}