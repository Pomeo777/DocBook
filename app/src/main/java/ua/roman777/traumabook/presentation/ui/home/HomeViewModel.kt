package ua.roman777.traumabook.presentation.ui.home

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.roman777.domain.models.Patient
import ua.roman777.domain.usecase.*

class HomeViewModel(private val addPatientUC: AddPatientUC,
                    private val addPatientImageUC: AddPatientImageUC,
                    private val getAllPatientFlowUC: GetAllPatientFlowUC
) : ViewModel() {

    lateinit var _patients: LiveData<MutableList<Patient>>
    val changedPatient by lazy { MutableLiveData<Patient>() }

    init{
        fetchLocalData()
    }

    private fun fetchLocalData() {
        viewModelScope.launch {
            _patients = getAllPatientFlowUC.getAllPatientFlow().asLiveData()
        }
    }

//    fun addPatient(patient: Patient){
//        viewModelScope.launch {
//            patientRepository.addNewUser(patient)
//        }
//    }

    fun addPatient(photoPath: String){
        viewModelScope.launch(Dispatchers.IO) {
            addPatientUC.addPatient(photoPath)
        }
    }


    suspend fun addPatientPhoto(pathList: List<String>) {
        changedPatient.value?.let {
            addPatientImageUC.addPatientPhoto(it, pathList)
        }

    }

    suspend fun addPatientPhoto(photoPath: String) {
        changedPatient.value?.let {
            addPatientImageUC.addPatientPhoto(it, photoPath)
        }
    }


}