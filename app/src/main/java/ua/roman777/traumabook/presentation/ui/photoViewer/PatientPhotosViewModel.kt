package ua.roman777.traumabook.presentation.ui.photoViewer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber
import ua.roman777.domain.models.Photo


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

}