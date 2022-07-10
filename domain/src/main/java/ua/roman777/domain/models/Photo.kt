package ua.roman777.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by Roman Fedchenko
 * date 26.05.2022
 * author email pomeo77777@gmail.com
 */
@Parcelize
data class Photo(var photoPath: String, var data: String, var description: String = "") :
    Parcelable {

    override fun toString(): String {
        return "Photo(photoPath='$photoPath', data='$data' description='$description')"
    }


}