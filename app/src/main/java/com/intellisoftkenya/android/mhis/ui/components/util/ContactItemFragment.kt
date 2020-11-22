package com.intellisoftkenya.android.mhis.ui.components.util

import android.view.View
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.intellisoftkenya.android.mhis.R

open class ContactItemFragment: Fragment() {
    private var currentPage: Int = 1
    protected var pageIds = arrayListOf(R.id.heightWeightView, R.id.bloodPressureView, R.id.maternalExamView)

    fun movePage(move: Int) {
        currentPage += move
        when (currentPage) {
            pageIds.size -> this.view?.findViewById<MaterialButton>(R.id.next)?.visibility = View.GONE
            1 -> this.view?.findViewById<MaterialButton>(R.id.previous)?.visibility = View.GONE
            else -> {
                this.view?.findViewById<MaterialButton>(R.id.previous)?.visibility = View.VISIBLE
                this.view?.findViewById<MaterialButton>(R.id.next)?.visibility = View.VISIBLE
            }
        }
        pageIds.forEachIndexed { index, element ->
            when (index) {
                currentPage - 1 -> this.toggleView(element, View.VISIBLE)
                else -> this.toggleView(element, View.GONE)
            }
        }
    }

    private fun toggleView(id: Int, visibility: Int) {
        this.view?.findViewById<RelativeLayout>(id)?.visibility = visibility
    }
}