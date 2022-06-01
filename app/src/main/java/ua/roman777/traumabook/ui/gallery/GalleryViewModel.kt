package ua.roman777.traumabook.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import ua.roman777.traumabook.dataBase.dataEntity.Patient
import ua.roman777.traumabook.dataBase.dataEntity.Photo
import ua.roman777.traumabook.services.PatientRepository
import java.util.stream.Collectors

class GalleryViewModel(private val patientRepository: PatientRepository) : ViewModel() {

    val photos = MutableLiveData<List<Photo>>()

    init{
        fetchLocalData()
    }

    private fun fetchLocalData() {
        viewModelScope.launch {
            val collectedPhoto = mutableListOf<Photo>()
            patientRepository.getAllPatient().last().forEach{ patient ->
                setImagesDescription(patient).let { collectedPhoto.addAll(it.images) }
            }
            photos.postValue(collectedPhoto)
        }
    }

    private fun setImagesDescription(patient: Patient): Patient {
        for(i in patient.images){
            i.description = patient.nameAndSurname
        }
        return patient
    }

    private fun collectImages(patients: List<Patient>): MutableList<Photo> {
        val photos = mutableListOf<Photo>()
        for(p in patients){
            photos.addAll(p.images)
        }
        return photos
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