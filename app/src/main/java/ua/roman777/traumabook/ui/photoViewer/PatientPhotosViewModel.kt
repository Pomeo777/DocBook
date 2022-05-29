package ua.roman777.traumabook.ui.photoViewer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber
import ua.roman777.traumabook.dataBase.dataEntity.Photo
import ua.roman777.traumabook.services.PatientRepository
import ua.roman777.traumabook.ui.home.HomeViewModel


/**
 * Created by Roman Fedchenko
 * date 26.05.2022
 * author email pomeo77777@gmail.com
 */
class PatientPhotosViewModel(private val photos: List<Photo>): ViewModel() {

    lateinit var photoFragments: MutableLiveData<MutableList<PhotoItemFragment>>

    init {
        viewModelScope.launch {
            makeFragments()
        }
    }

    private  fun makeFragments(){
        val list = mutableListOf<PhotoItemFragment>()
        for(p in photos){
            list.add(PhotoItemFragment.instance(p))
        }
        Timber.d("makeFragments(). list = %s", list)
        photoFragments = MutableLiveData(list)
    }

    class PatientPhotosViewModelFactory(private val photos: List<Photo>)
        : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PatientPhotosViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return PatientPhotosViewModel(photos) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }
    }
}