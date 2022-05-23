package ua.roman777.traumabook.dataBase.utils

import androidx.room.TypeConverter
import java.util.*


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
class ImagesListConverter {

    @TypeConverter
    fun fromList(list: List<String>): String {
        return if (list.isNotEmpty()) {
            val sb = StringBuilder()
            var sep = ""
            for (s in list) {
                sb.append(sep).append(s)
                sep = ","
            }
            sb.toString()
        } else {
            ""
        }
    }

    @TypeConverter
    fun toList(data: String): List<String> {
        data.trim { it <= ' ' }
        return if (data.isEmpty()) mutableListOf() else listOf(*data.split(",").toTypedArray())
    }
}