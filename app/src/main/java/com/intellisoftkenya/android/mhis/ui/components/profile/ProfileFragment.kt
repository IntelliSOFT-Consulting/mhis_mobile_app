package com.intellisoftkenya.android.mhis.ui.components.profile

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.intellisoftkenya.android.mhis.R
import com.intellisoftkenya.android.mhis.ui.components.util.ContactItemFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import java.text.SimpleDateFormat
import java.util.*


class ProfileFragment : ContactItemFragment(), DatePickerDialog.OnDateSetListener {

    companion object {
        fun newInstance() = ProfileFragment()
    }
    private lateinit var viewModel: ProfileViewModel
    val myCalendar: Calendar = Calendar.getInstance()
    private var dateInput: TextInputEditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root: View = inflater.inflate(R.layout.fragment_profile, container, false)
        var next: MaterialButton = root.findViewById(R.id.next)
        next.setOnClickListener { this.movePage(1) }

        var previous: MaterialButton = root.findViewById(R.id.previous)
        previous.visibility = View.GONE
        previous.setOnClickListener { this.movePage(-1) }
        this.pageIds = arrayListOf(R.id.demographicView, R.id.pregnancyView, R.id.obstetricHistoryView,
        R.id.medicalHistoryView, R.id.immunizationView, R.id.medicationView, R.id.behaviourView, R.id.partnerView)

        val occupationChecks = root.findViewById<LinearLayout>(R.id.occupationChecks)
        var i = 0
        while (i < occupationChecks.childCount) {
            val v = occupationChecks.getChildAt(i) as MaterialCheckBox
            v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }

        var lpmKnownGroup = root.findViewById<RadioGroup>(R.id.lpmKnownGroup)
        lpmKnownGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.lmpKnownYes == checkedId) {
                root.findViewById<LinearLayout>(R.id.lpmDateView).visibility = View.VISIBLE
            } else {
                root.findViewById<LinearLayout>(R.id.lpmDateView).visibility = View.GONE
            }
        }

        var ultraSoundDone = root.findViewById<RadioGroup>(R.id.ultraSoundDone)
        ultraSoundDone.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.ultrasoundDoneYes == checkedId) {
                root.findViewById<LinearLayout>(R.id.ultraSoundDetailsView).visibility = View.VISIBLE
            } else {
                root.findViewById<TextInputEditText>(R.id.ultraSoundDetailsView).visibility = View.GONE
            }
        }

        val allergiesView = root.findViewById<LinearLayout>(R.id.allergiesView)
        i = 0
        while (i < allergiesView.childCount) {
            val v = allergiesView.getChildAt(i) as MaterialCheckBox
            v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }
        val surgeriesView = root.findViewById<LinearLayout>(R.id.surgeriesView)
        i = 0
        while (i < surgeriesView.childCount) {
            val v = surgeriesView.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }
        val conditionsView = root.findViewById<LinearLayout>(R.id.conditionsView)
        i = 0
        while (i < conditionsView.childCount) {
            val v = conditionsView.getChildAt(i) as MaterialCheckBox
            v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }
        val currentMedication = root.findViewById<LinearLayout>(R.id.currentmedicationView)
        i = 0
        while (i < currentMedication.childCount) {
            val v = currentMedication.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }
        val alcoholSubstance = root.findViewById<LinearLayout>(R.id.alcoholSubstanceView)
        i = 0
        while (i < alcoholSubstance.childCount) {
            val v = alcoholSubstance.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }

        val lpmDate = root.findViewById<EditText>(R.id.lmpDate)
        lpmDate.setOnClickListener { v ->
            onDateInputClick(v as TextInputEditText)
        }
        val ultraSoundDate = root.findViewById<EditText>(R.id.ultraSoundDate)
        ultraSoundDate.setOnClickListener { v ->
            onDateInputClick(v as TextInputEditText)
        }
        return root
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        myCalendar[Calendar.YEAR] = year
        myCalendar[Calendar.MONTH] = month
        myCalendar[Calendar.DAY_OF_MONTH] = day
        updateDate()
    }

    private fun onDateInputClick(v: TextInputEditText) {
        dateInput = v as TextInputEditText
        DatePickerDialog(
            this.context as Context, this, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
            myCalendar[Calendar.DAY_OF_MONTH]
        ).show()
    }

    private fun updateDate() {
        val myFormat = "dd/MM/yy" //In which you need put here

        val sdf = SimpleDateFormat(myFormat, Locale.US)
        dateInput?.setText(sdf.format(myCalendar.time))
        dateInput = null
    }

    private fun onCheckChange(checkbox: MaterialCheckBox, isChecked: Boolean) {
        if (checkbox.tag != null) {
            when (checkbox.tag.toString()) {
                "employmentOtherCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.employmentOther)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "otherAllergyCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.otherAllergy)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "otherSurgeriesCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.otherSurgeries)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "otherConditionCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.otherCondition)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "otherProceduresCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.otherProcedures)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "otherMedicationCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.otherMedication)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "otherSubstanceCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.otherSubstance)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }


}