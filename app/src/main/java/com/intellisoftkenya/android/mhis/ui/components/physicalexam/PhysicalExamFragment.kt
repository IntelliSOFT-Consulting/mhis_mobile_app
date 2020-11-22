package com.intellisoftkenya.android.mhis.ui.components.physicalexam

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.RelativeLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.intellisoftkenya.android.mhis.R
import com.intellisoftkenya.android.mhis.ui.components.util.ContactItemFragment

class PhysicalExamFragment : ContactItemFragment() {

    companion object {
        fun newInstance() = PhysicalExamFragment()
    }

    private lateinit var viewModel: PhysicalExamViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root: View = inflater.inflate(R.layout.fragment_physical_exam, container, false)
        var next: MaterialButton = root.findViewById(R.id.next)
        next.setOnClickListener { this.movePage(1) }

        var previous: MaterialButton = root.findViewById(R.id.previous)
        previous.visibility = View.GONE
        previous.setOnClickListener { this.movePage(-1) }
        this.pageIds = arrayListOf(R.id.heightWeightView, R.id.bloodPressureView, R.id.maternalExamView)

        val preGestationalWeightUnknown = root.findViewById<MaterialCheckBox>(R.id.preGestationalWeightUnknown)
        preGestationalWeightUnknown.setOnCheckedChangeListener { buttonView, isChecked ->
            this.view?.findViewById<TextInputEditText>(R.id.preGestationalWeight)?.visibility = if (isChecked)  View.GONE else View.VISIBLE
        }
        val unableToRecordBP = root.findViewById<MaterialCheckBox>(R.id.unableToRecordBP)
        unableToRecordBP.setOnCheckedChangeListener { buttonView, isChecked ->
            this.view?.findViewById<TextInputEditText>(R.id.unableToRecordBPReasonView)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
            this.view?.findViewById<TextInputEditText>(R.id.pressureView)?.visibility = if (isChecked)  View.GONE else View.VISIBLE
        }

        val respiratoryExamGroup = root.findViewById<RadioGroup>(R.id.respiratoryExamGroup)
        val abnormalRespiratoryChecked = root.findViewById<LinearLayout>(R.id.abnormalRespiratoryChecked)
        respiratoryExamGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.respiratoryAbnormal == checkedId) {
                abnormalRespiratoryChecked?.visibility = View.VISIBLE
            } else {
                abnormalRespiratoryChecked?.visibility = View.GONE
            }
        }
        var i = 0
        while (i < abnormalRespiratoryChecked.childCount) {
            val v = abnormalRespiratoryChecked.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }
        val cardiacExamGroup = root.findViewById<RadioGroup>(R.id.cardiacExamGroup)
        val cardiacAbnormalChecked = root.findViewById<LinearLayout>(R.id.cardiacAbnormalChecked)
        cardiacExamGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.cardiacAbnormal == checkedId) {
                cardiacAbnormalChecked?.visibility = View.VISIBLE
            } else {
                cardiacAbnormalChecked?.visibility = View.GONE
            }
        }

        i = 0
        while (i < cardiacAbnormalChecked.childCount) {
            val v = cardiacAbnormalChecked.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }

        val breastExamGroup = root.findViewById<RadioGroup>(R.id.breastExamGroup)
        val breastExamAbnormalChecked = root.findViewById<LinearLayout>(R.id.breastExamAbnormalChecked)
        breastExamGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.breastAbnormal == checkedId) {
                cardiacAbnormalChecked?.visibility = View.VISIBLE
            } else {
                cardiacAbnormalChecked?.visibility = View.GONE
            }
        }

        i = 0
        while (i < breastExamAbnormalChecked.childCount) {
            val v = breastExamAbnormalChecked.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }

        val abdominalExamGroup = root.findViewById<RadioGroup>(R.id.abdominalExamGroup)
        val abdominalExamAbnormalChecked = root.findViewById<LinearLayout>(R.id.abdominalExamAbnormalChecked)
        abdominalExamGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.abdominalAbnormal == checkedId) {
                cardiacAbnormalChecked?.visibility = View.VISIBLE
            } else {
                cardiacAbnormalChecked?.visibility = View.GONE
            }
        }

        i = 0
        while (i < abdominalExamAbnormalChecked.childCount) {
            val v = abdominalExamAbnormalChecked.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }

        val pelvicExamGroup = root.findViewById<RadioGroup>(R.id.pelvicExamGroup)
        val pelvicExamAbnormalChecked = root.findViewById<LinearLayout>(R.id.abdominalExamAbnormalChecked)
        pelvicExamGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.pelvicAbnormal == checkedId) {
                cardiacAbnormalChecked?.visibility = View.VISIBLE
            } else {
                cardiacAbnormalChecked?.visibility = View.GONE
            }
        }

        i = 0
        while (i < pelvicExamAbnormalChecked.childCount) {
            val v = pelvicExamAbnormalChecked.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }

        val cervicalExamGroup = root.findViewById<RadioGroup>(R.id.cervicalExamGroup)
        cervicalExamGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.cervicalDone == checkedId) {
                this.view?.findViewById<LinearLayout>(R.id.dilationView)?.visibility = View.VISIBLE
            } else {
                this.view?.findViewById<LinearLayout>(R.id.dilationView)?.visibility = View.GONE
            }
        }

        val oedemaPresentGroup = root.findViewById<RadioGroup>(R.id.oedemaPresentGroup)
        val oedemaPresentChecks = root.findViewById<LinearLayout>(R.id.oedemaPresentChecks)
        oedemaPresentGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.oedemaYes == checkedId) {
                oedemaPresentChecks?.visibility = View.VISIBLE
            } else {
                oedemaPresentChecks?.visibility = View.GONE
            }
        }

        i = 0
        while (i < oedemaPresentChecks.childCount) {
            val v = oedemaPresentChecks.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }

        val heartBeatPresentGroup = root.findViewById<RadioGroup>(R.id.heartBeatPresentGroup)
        val heartRateView = root.findViewById<LinearLayout>(R.id.heartRateView)
        heartBeatPresentGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.heartBeatYes == checkedId) {
                heartRateView?.visibility = View.VISIBLE
            } else {
                heartRateView?.visibility = View.GONE
            }
        }

        val noFetusesUnknown = root.findViewById<MaterialCheckBox>(R.id.noFetusesUnknown)
        noFetusesUnknown.setOnCheckedChangeListener { buttonView, isChecked ->
            this.view?.findViewById<LinearLayout>(R.id.noFetusesView)?.visibility = if (isChecked)  View.GONE else View.VISIBLE
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PhysicalExamViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun onCheckChange(checkbox: MaterialCheckBox, isChecked: Boolean) {
        if (checkbox.tag != null) {
            when (checkbox.tag.toString()) {
                "abnormalRespiratoryOtherCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.abnormalRespiratoryOther)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "abnormalCardiacOtherCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.abnormalCardiacOther)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "abnormalBreastExamOtherCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.abnormalBreastExamOther)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "abdominalExamOtherCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.abdominalExamOther)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "pelvicExamOtherCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.pelvicExamOther)?.visibility = if (isChecked)  View.VISIBLE else View.GONE

            }
        }
    }

}