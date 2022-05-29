package ua.roman777.traumabook.ui.home

import android.content.Context
import android.net.Uri
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import timber.log.Timber
import ua.roman777.traumabook.application.MyAppGlideModel
import ua.roman777.traumabook.application.TBookApplication
import ua.roman777.traumabook.dataBase.dataEntity.Patient
import ua.roman777.traumabook.dataBase.dataEntity.Photo
import ua.roman777.traumabook.services.PatientRepository
import ua.roman777.traumabook.utils.ImageFilePathDeterminer
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(private val patientRepository: PatientRepository) : ViewModel() {

    lateinit var _patients: LiveData<MutableList<Patient>>
    val changedPatient by lazy { MutableLiveData<Patient>() }

    init{
        fetchLocalData()
    }

    private fun fetchLocalData() {
        viewModelScope.launch {
            _patients = patientRepository.getAllPatient().asLiveData()
        }
    }

    fun addPatient(patient: Patient){
        viewModelScope.launch {
            patientRepository.addNewUser(patient)
        }
    }

    fun addPatient(photoPath: String){
        viewModelScope.launch {
            val patient = createPatient(photoPath)
            Timber.d("registerForActivityResult(). new patient: %s", patient.toString())
            patientRepository.addNewUser(patient)
        }
    }



    fun updatePatient(patient: Patient){
        viewModelScope.launch {
            patientRepository.updateUser(patient)
        }
    }

    fun updatePatientPhoto(pathList: List<String>) {
        changedPatient.value?.let{ p ->
            for(path in pathList){
                Timber.d("getGalleryImageContracts. registerForActivityResult(). uri.path: %s", path)
                Photo(path, getDate() ).let { p.images.add(it) }
            }
            updatePatient(changedPatient.value!!)
        }
    }

    fun updatePatientPhoto(photoPath: String) {
        changedPatient.value?.let{ p ->
            Timber.d("takeCameraImageContracts. registerForActivityResult(). homeViewModel.changedPatient.value = %s", p)
            p.images.add(Photo("file://+$photoPath", getDate() ))
            Timber.d("takeCameraImageContracts. registerForActivityResult(). homeViewModel.changedPatient.value = %s", p)
            updatePatient(changedPatient.value!!)
        }

    }

    private fun createPatient(photoPath: String): Patient{
        Timber.d("createPatient(). photoPath: %s", photoPath)
        val patient = Patient()
        patient.patientId = Calendar.getInstance().timeInMillis.toString()
        patient.date = getDate()
        patient.images.add(Photo("file://+$photoPath", patient.date))
        return patient
    }

    private fun getDate(): String{
        val comDate: Date = Calendar.getInstance().time
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(comDate)
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