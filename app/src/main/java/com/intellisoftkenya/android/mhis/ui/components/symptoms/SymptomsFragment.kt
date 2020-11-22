package com.intellisoftkenya.android.mhis.ui.components.symptoms

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.intellisoftkenya.android.mhis.R
import com.intellisoftkenya.android.mhis.ui.components.util.ContactItemFragment

class SymptomsFragment : ContactItemFragment() {

    companion object {
        fun newInstance() = SymptomsFragment()
    }
    private lateinit var viewModel: SymptomsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root: View = inflater.inflate(R.layout.fragment_symptoms, container, false)
        var next: MaterialButton = root.findViewById(R.id.next)
        next.setOnClickListener { this.movePage(1) }

        var previous: MaterialButton = root.findViewById(R.id.previous)
        previous.visibility = View.GONE
        previous.setOnClickListener { this.movePage(-1) }
        this.pageIds = arrayListOf(R.id.medicationFollowupView, R.id.psychologicalSymptomsView)

        val medicationFollowupChecks = root.findViewById<LinearLayout>(R.id.medicationFollowupChecks)
        var i = 0
        while (i < medicationFollowupChecks.childCount) {
            val v = medicationFollowupChecks.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }
        val otherSymptomsView = root.findViewById<LinearLayout>(R.id.otherSymptomsView)
        i = 0
        while (i < otherSymptomsView.childCount) {
            val v = otherSymptomsView.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }
        val psychologicalSymptomsCheckView = root.findViewById<LinearLayout>(R.id.psychologicalSymptomsCheckView)
        i = 0
        while (i < psychologicalSymptomsCheckView.childCount) {
            val v = psychologicalSymptomsCheckView.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SymptomsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun onCheckChange(checkbox: MaterialCheckBox, isChecked: Boolean) {
        if (checkbox.tag != null) {
            when (checkbox.tag.toString()) {
                "otherMedicationCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.otherMedication)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "otherSymptomsCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.otherSymptoms)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "oedema" , "varicoseVeins" ->
                    this.view?.findViewById<TextInputEditText>(R.id.varicoseVeinsSymptomsView)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "pelvicPain", "lowBackPain" ->
                    this.view?.findViewById<TextInputEditText>(R.id.lowBackPainSymptomsView)?.visibility = if (isChecked)  View.VISIBLE else View.GONE

            }
        }
    }
}