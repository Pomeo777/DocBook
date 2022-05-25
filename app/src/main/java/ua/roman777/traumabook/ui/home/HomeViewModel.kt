package ua.roman777.traumabook.ui.home

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ua.roman777.traumabook.dataBase.dataEntity.Patient
import ua.roman777.traumabook.services.PatientRepository

class HomeViewModel(private val patientRepository: PatientRepository) : ViewModel() {

    lateinit var _patients: LiveData<MutableList<Patient>>

    init{
        fetchLocalData()
    }

    private fun fetchLocalData() {
        viewModelScope.launch {
            _patients = patientRepository.getAllPatient().asLiveData()
        }
    }

    private fun addPatient(patient: Patient){
        viewModelScope.launch {
            patientRepository.addNewUser(patient)
        }
    }

    class HomeViewModelFactory(private val patientRepository: PatientRepository)
        :ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(patientRepository) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }
        }


}