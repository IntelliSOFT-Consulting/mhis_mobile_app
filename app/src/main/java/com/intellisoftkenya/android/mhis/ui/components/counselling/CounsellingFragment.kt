package com.intellisoftkenya.android.mhis.ui.components.counselling

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

class CounsellingFragment : ContactItemFragment() {

    companion object {
        fun newInstance() = CounsellingFragment()
    }
    private lateinit var viewModel: CounsellingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root: View = inflater.inflate(R.layout.fragment_counselling, container, false)
        var next: MaterialButton = root.findViewById(R.id.next)
        next.setOnClickListener { this.movePage(1) }

        var previous: MaterialButton = root.findViewById(R.id.previous)
        previous.visibility = View.GONE
        previous.setOnClickListener { this.movePage(-1) }
        this.pageIds = arrayListOf(R.id.referralView, R.id.dietCounsellingView,
        R.id.counsellingView, R.id.ipvView, R.id.recommendationView)

        val referralGroup = root.findViewById<RadioGroup>(R.id.hospitalReferral)
        val noReferralReasonView = root.findViewById<LinearLayout>(R.id.noReferralReasonView)
        referralGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.referredNo == checkedId) {
                noReferralReasonView?.visibility = View.VISIBLE
            } else {
                noReferralReasonView?.visibility = View.GONE
            }
        }

        var i = 0
        while (i < noReferralReasonView.childCount) {
            val v = noReferralReasonView.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }

        val healthEatingGroup = root.findViewById<RadioGroup>(R.id.healthEatingGroup)
        val dietNotDoneView = root.findViewById<LinearLayout>(R.id.dietNotDoneView)
        healthEatingGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.healthEatingNotDone == checkedId) {
                dietNotDoneView?.visibility = View.VISIBLE
            } else {
                dietNotDoneView?.visibility = View.GONE
            }
        }

        i = 0
        while (i < dietNotDoneView.childCount) {
            val v = dietNotDoneView.getChildAt(i) as View
            if (v is MaterialCheckBox)
                v.setOnCheckedChangeListener { buttonView, isChecked -> this.onCheckChange(buttonView as MaterialCheckBox, isChecked) }
            i++
        }

        val postpartumFPView = root.findViewById<RadioGroup>(R.id.postpartumFPView)
        postpartumFPView.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.postpartumDone == checkedId) {
                this.view?.findViewById<LinearLayout>(R.id.fpMethodView)?.visibility = View.VISIBLE
            } else {
                this.view?.findViewById<LinearLayout>(R.id.fpMethodView)?.visibility = View.GONE
            }
        }
        val ipvCheckGroup = root.findViewById<RadioGroup>(R.id.ipvCheckGroup)
        ipvCheckGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.ipvDone == checkedId) {
                this.view?.findViewById<LinearLayout>(R.id.ipvEnquiryResultsView)?.visibility = View.VISIBLE
                this.view?.findViewById<LinearLayout>(R.id.ipvNotDoneView)?.visibility = View.GONE
            } else {
                this.view?.findViewById<LinearLayout>(R.id.ipvEnquiryResultsView)?.visibility = View.GONE
                this.view?.findViewById<LinearLayout>(R.id.ipvNotDoneView)?.visibility = View.VISIBLE
            }
        }
        val ipvNotDoneGroup = root.findViewById<RadioGroup>(R.id.ipvNotDoneGroup)
        ipvNotDoneGroup.setOnCheckedChangeListener {group, checkedId ->
            if (R.id.ipvOtherSpecify == checkedId) {
                this.view?.findViewById<LinearLayout>(R.id.ipvReasonOther)?.visibility = View.VISIBLE
            } else {
                this.view?.findViewById<LinearLayout>(R.id.ipvReasonOther)?.visibility = View.GONE
            }
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CounsellingViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun onCheckChange(checkbox: MaterialCheckBox, isChecked: Boolean) {
        if (checkbox.tag != null) {
            when (checkbox.tag.toString()) {
                "referredOtherCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.referredOther)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "otherReasonCheck" ->
                    this.view?.findViewById<TextInputEditText>(R.id.otherReason)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "oedema" , "varicoseVeins" ->
                    this.view?.findViewById<TextInputEditText>(R.id.varicoseVeinsSymptomsView)?.visibility = if (isChecked)  View.VISIBLE else View.GONE
                "pelvicPain", "lowBackPain" ->
                    this.view?.findViewById<TextInputEditText>(R.id.lowBackPainSymptomsView)?.visibility = if (isChecked)  View.VISIBLE else View.GONE

            }
        }
    }

}