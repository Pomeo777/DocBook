package ua.roman777.traumabook.application

import android.view.View
import com.google.gson.Gson
import timber.log.Timber
import ua.roman777.traumabook.dataBase.dataEntity.Photo


/**
 * Created by Roman Fedchenko
 * date 26.05.2022
 * author email pomeo77777@gmail.com
 */

fun Gson.stringToPhotoObject(string: String): Photo {
    Timber.d("Gson. stringToPhotoObject(). string: %s", string)
    return Gson().fromJson<Photo>(string, Photo::class.java)
}

fun Gson.photoObjectToString(photo: Photo): String{
    return Gson().toJson(photo)
}