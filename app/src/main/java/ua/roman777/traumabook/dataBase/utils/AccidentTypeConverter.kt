package ua.roman777.traumabook.dataBase.utils

import androidx.room.TypeConverter
import ua.roman777.traumabook.dataBase.dataEnums.AccidentType


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
class AccidentTypeConverter {
    @TypeConverter
    fun toHealth(value: Int): String = enumValues<AccidentType>()[value].acName

    @TypeConverter
    fun fromHealth(value: AccidentType): Int = value.ordinal

    companion object StaticConvertor{
        fun toHealth(value: Int): String = enumValues<AccidentType>()[value].acName
    }

}