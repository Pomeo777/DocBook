package ua.roman777.traumabook.presentation.ui.editPatient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ua.roman777.domain.models.Patient
import ua.roman777.domain.usecase.EditPatientUC

class EditPatientDataViewModel(private val updatePatientUC: EditPatientUC) : ViewModel() {

    val patientLiveData: MutableLiveData<Patient> = MutableLiveData<Patient>()
    val updateUserData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun updatePatient(){
        viewModelScope.launch(Dispatchers.IO) {
            patientLiveData.value?.let {

                val res: Deferred<Int> = async {
                    updatePatientUC.editPatient(it)
                }
                if (res.await() >= 1) {
                    updateUserData.postValue(true)
                }


            }
        }
    }
}