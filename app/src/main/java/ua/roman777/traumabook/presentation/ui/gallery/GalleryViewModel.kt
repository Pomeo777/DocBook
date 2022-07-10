package ua.roman777.traumabook.presentation.ui.gallery

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.roman777.domain.models.Photo
import ua.roman777.domain.usecase.GetAllPatientsImagesUC

class GalleryViewModel(private val getAllPatientsImagesUC: GetAllPatientsImagesUC) : ViewModel() {

    val photos = MutableLiveData<List<Photo>>()

    init{
        fetchLocalData()
    }

    private fun fetchLocalData() {
        viewModelScope.launch(Dispatchers.IO) {
                photos.postValue(getAllPatientsImagesUC.getAllPatientsImages())
        }
    }



}