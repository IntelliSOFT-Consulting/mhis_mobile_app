package com.intellisoftkenya.android.mhis.ui.components.registration

import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.intellisoftkenya.android.mhis.R
import com.intellisoftkenya.android.mhis.ui.components.form.Button
import com.intellisoftkenya.android.mhis.ui.components.form.Checkbox
import com.intellisoftkenya.android.mhis.ui.components.form.DatePicker
import com.intellisoftkenya.android.mhis.ui.components.form.TextInput
import org.hl7.fhir.r4.model.Address
import org.hl7.fhir.r4.model.ContactPoint
import org.hl7.fhir.r4.model.HumanName
import org.hl7.fhir.r4.model.Patient
import java.text.SimpleDateFormat
import java.util.*

class RegistrationFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    companion object {
        fun newInstance() = RegistrationFragment()
    }
    private lateinit var viewModel: RegistrationViewModel
    val myCalendar: Calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val root = inflater.inflate(R.layout.fragment_registration, container, false)
        val linearLayout = root.findViewById<LinearLayout>(R.id.mainLayout)

        viewModel =
            ViewModelProviders.of(this).get(RegistrationViewModel::class.java)

        /*val id: TextInputLayout = TextInput(inflater.context, "ANC ID").getComponent()
        linearLayout.addView(id)
        linearLayout.addView(TextInput(inflater.context, "First name").getComponent())
        linearLayout.addView(TextInput(inflater.context, "Last name").getComponent())
        linearLayout.addView(DatePicker(inflater.context, "Date of birth", childFragmentManager).getComponent())
        linearLayout.addView(Checkbox(inflater.context, "Date of birth unknown", false).getComponent())
        linearLayout.addView(TextInput(inflater.context, "Age", EditorInfo.TYPE_CLASS_NUMBER).getComponent())
        linearLayout.addView(TextInput(inflater.context, "Home address").getComponent())
        linearLayout.addView(TextInput(inflater.context, "Phone number", EditorInfo.TYPE_CLASS_PHONE).getComponent())
        linearLayout.addView(TextInput(inflater.context, "Alternate contact name").getComponent())
        linearLayout.addView(TextInput(inflater.context, "Alternate contact Phone number", EditorInfo.TYPE_CLASS_PHONE).getComponent())
        linearLayout.addView(Button(inflater.context, "Save").getComponent())*/
        val registerBtn = root.findViewById<MaterialButton>(R.id.registerPatient)
        registerBtn.setOnClickListener { onRegisterPatient() }

        val lpmDate = root.findViewById<EditText>(R.id.dob)
        lpmDate.setOnClickListener { v ->
            DatePickerDialog(
                this.context as Context, this, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
        return root
    }

    override fun onDateSet(view: android.widget.DatePicker, year: Int, month: Int, day: Int) {
        myCalendar[Calendar.YEAR] = year
        myCalendar[Calendar.MONTH] = month
        myCalendar[Calendar.DAY_OF_MONTH] = day

        val myFormat = "dd/MM/yy" //In which you need put here

        val sdf = SimpleDateFormat(myFormat, Locale.US)
        this.view?.findViewById<TextInputEditText>(R.id.dob)?.setText(sdf.format(myCalendar.time))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegistrationViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun onRegisterPatient() {
        val navController = this.findNavController()

        val patient = Patient();

        val firstName = this.view?.findViewById<TextInputEditText>(R.id.firstName);
        val names = HumanName()
        names.addGiven(firstName?.text.toString())

        val lastName = this.view?.findViewById<TextInputEditText>(R.id.lastName);
        names.family = lastName?.text.toString()
        patient.addName(names)

        val dob = this.view?.findViewById<TextInputEditText>(R.id.dob)
        if (dob?.text.toString() != "") {
            patient.birthDate = myCalendar.time
        }

        val homeAddress = this.view?.findViewById<TextInputEditText>(R.id.homeAddress);
        val address = Address();
        address.addLine(homeAddress?.text.toString());
        patient.addAddress(address)

        val phone = this.view?.findViewById<TextInputEditText>(R.id.phoneNumber)?.text.toString()
        val telecom = ContactPoint()
        telecom.value = phone
        patient.addTelecom(telecom)

        val alternateFirstName = this.view?.findViewById<TextInputEditText>(R.id.alternateFirstName)?.text.toString()
        val alternateLastName = this.view?.findViewById<TextInputEditText>(R.id.alternateLastName)?.text.toString()
        val contact = Patient.ContactComponent()
        val contactName = HumanName()
        contactName.addGiven(alternateFirstName)
        contactName.family = alternateLastName
        contact.name = contactName

        val alternatePhone = this.view?.findViewById<TextInputEditText>(R.id.alternateContactPhone)?.text.toString()
        val alternateTelecom = ContactPoint()
        alternateTelecom.value = alternatePhone
        contact.addTelecom(alternateTelecom)
        patient.addContact(contact)


        navController.navigate(R.id.nav_quick_check)
    }

}