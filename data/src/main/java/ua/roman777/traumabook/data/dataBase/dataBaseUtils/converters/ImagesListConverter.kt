package ua.roman777.traumabook.data.dataBase.dataBaseUtils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import ua.roman777.domain.models.Photo
import ua.roman777.traumabook.data.dataBase.dataBaseUtils.photoObjectToString
import ua.roman777.traumabook.data.dataBase.dataBaseUtils.stringToPhotoObject


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
class ImagesListConverter {

    @TypeConverter
    fun fromList(list: MutableList<Photo>): String {
        return if (list.isNotEmpty()) {
            val sb = StringBuilder()
            var sep = ""
            for (s in list) {
                val string = Gson().photoObjectToString(s)
                sb.append(sep).append(string)
                sep = ";"
            }
            sb.toString()
        } else {
            ""
        }
    }

    @TypeConverter
    fun toList(data: String): MutableList<Photo> {
        data.trim { it <= ' ' }
        return if (data.isEmpty()) mutableListOf() else mutableListOf(*data.split(";")
            .map { obj -> Gson().stringToPhotoObject(obj) }
            .toTypedArray())
    }



    companion object {
        @JvmStatic lateinit var instance: ImagesListConverter
    }

    init {
        instance = this
    }
}