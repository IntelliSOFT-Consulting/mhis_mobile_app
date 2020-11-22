package com.intellisoftkenya.android.mhis.ui.components.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.hl7.fhir.r4.model.HumanName
import org.hl7.fhir.r4.model.Patient


class RegistrationViewModel : ViewModel() {

    private val _patient = MutableLiveData<Patient>()

    val id = MutableLiveData<String>().apply { value = "" }
    private val firstName = MutableLiveData<String>().apply { value = "" }
    private val lastName = MutableLiveData<String>().apply { value = "" }
    private val dob = MutableLiveData<String>().apply { value = "" }
    private val age = MutableLiveData<String>().apply { value = "" }
    private val address = MutableLiveData<String>().apply { value = "" }
    private val phoneNumber = MutableLiveData<String>().apply { value = "" }
    private val contactName = MutableLiveData<String>().apply { value = "" }
    private val contactAddress = MutableLiveData<String>().apply { value = "" }



}