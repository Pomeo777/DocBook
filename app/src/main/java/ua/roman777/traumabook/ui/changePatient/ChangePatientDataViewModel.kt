package ua.roman777.traumabook.ui.changePatient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ua.roman777.traumabook.dataBase.dataEntity.Patient
import ua.roman777.traumabook.services.PatientRepository

class ChangePatientDataViewModel(private val patientRepository: PatientRepository) : ViewModel() {

    val patientLiveData: MutableLiveData<Patient> = MutableLiveData<Patient>()
    val updateUserData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun updatePatient(){
        viewModelScope.launch {
            patientLiveData.value?.let {

                val res: Deferred<Int> = async {
                    patientRepository.updateUser(it)
                }
                if (res.await() >= 1) {
                    updateUserData.postValue(true)
                }


            }
        }
    }

    class ChangePatientDataViewModelFactory(private val patientRepository: PatientRepository)
        : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ChangePatientDataViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return ChangePatientDataViewModel(patientRepository) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }
    }
}