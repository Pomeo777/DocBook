package ua.roman777.domain


import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Roman Fedchenko
 * date 26.05.2022
 * author email pomeo77777@gmail.com
 */



fun getDate(): String{
    val comDate: Date = Calendar.getInstance().time
    val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return dateFormat.format(comDate)
}