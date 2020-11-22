package com.intellisoftkenya.android.mhis.ui.components.quickcheck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.intellisoftkenya.android.mhis.R


class QuickCheckFragment : Fragment() {

    companion object {
        fun newInstance() = QuickCheckFragment()
    }

    private lateinit var viewModel: QuickCheckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_quick_check, container, false)
        val startContactBtn = root.findViewById<MaterialButton>(R.id.startContactBtn)
        startContactBtn.setOnClickListener { startContact() }

        val reasonGroup = root.findViewById<RadioGroup>(R.id.reasonGroup)
        reasonGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.specificComplaint == checkedId) {
                this.view?.findViewById<LinearLayout>(R.id.specificComplaints)?.visibility = View.VISIBLE
            } else {
                this.view?.findViewById<LinearLayout>(R.id.specificComplaints)?.visibility = View.GONE
            }
        }
        val dangerSigns = root.findViewById<LinearLayout>(R.id.dangerSignsLayout)
        val noneDanger = root.findViewById<MaterialCheckBox>(R.id.none)
        noneDanger.setOnCheckedChangeListener {buttonView, isChecked ->
            this.view?.findViewById<RelativeLayout>(R.id.buttonViews)?.visibility = View.VISIBLE
            if (isChecked) {
                startContactBtn.visibility = View.VISIBLE
                this.view?.findViewById<MaterialButton>(R.id.referPatient)?.visibility = View.GONE
                val dangerSigns = root.findViewById<LinearLayout>(R.id.dangerSignsLayout)
                var x = 0
                while (x < dangerSigns.childCount) {
                    val v = dangerSigns.getChildAt(x) as MaterialCheckBox
                    v.isChecked = false
                    x++
                }
            } else {
                // startContactBtn.visibility = View.GONE
            }
        }
        var x = 0
        while (x < dangerSigns.childCount) {
            val v = dangerSigns.getChildAt(x) as MaterialCheckBox
            v.setOnCheckedChangeListener { buttonView, isChecked -> this.dangerSignChecked(isChecked) }
            x++
        }
        return root
    }

    private fun dangerSignChecked(isChecked: Boolean) {
        if (isChecked) {
            val startContactBtn = this.view?.findViewById<MaterialButton>(R.id.startContactBtn)
            startContactBtn?.visibility = View.GONE
            val referBtn = this.view?.findViewById<MaterialButton>(R.id.referPatient)
            referBtn?.visibility = View.VISIBLE
            this.view?.findViewById<MaterialCheckBox>(R.id.none)?.isChecked = false
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(QuickCheckViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun startContact() {
        val navController = this.findNavController()
        navController.navigate(R.id.nav_contact)
    }

}