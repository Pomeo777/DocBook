package ua.roman777.traumabook.ui.gallery

import androidx.lifecycle.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import timber.log.Timber
import ua.roman777.traumabook.dataBase.dataEntity.Patient
import ua.roman777.traumabook.dataBase.dataEntity.Photo
import ua.roman777.traumabook.services.PatientRepository

class GalleryViewModel(private val patientRepository: PatientRepository) : ViewModel() {

    val photos = MutableLiveData<List<Photo>>()

    init{
        fetchLocalData()
    }

    private fun fetchLocalData() {
        viewModelScope.launch(Dispatchers.IO) {
            val collectedPhoto = mutableListOf<Photo>()
            patientRepository.getAllPatient().forEach{ patient ->
                setImagesDescription(patient).let { collectedPhoto.addAll(it.images) }
            }.also {
                photos.postValue(collectedPhoto)
            }
        }
    }

    private fun setImagesDescription(patient: Patient): Patient {
        for(i in patient.images){
            i.description = patient.nameAndSurname
        }
        return patient
    }

    class GalleryViewModelFactory(private val patientRepository: PatientRepository)
        : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GalleryViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return GalleryViewModel(patientRepository) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }
    }
}