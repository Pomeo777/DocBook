package ua.roman777.traumabook.dataBase.dataEntity

import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.text.TextUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.room.*
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.parcelize.Parcelize
import ua.roman777.traumabook.dataBase.PatientDB.Companion.DATABASE_NAME
import ua.roman777.traumabook.dataBase.dataEnums.AccidentType
import ua.roman777.traumabook.dataBase.utils.AccidentTypeConverter
import ua.roman777.traumabook.dataBase.utils.ImagesListConverter


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
@Entity(tableName = DATABASE_NAME)
@Parcelize
data class Patient(
    @PrimaryKey @ColumnInfo(name = PATIENT_ID) var patientId: String = "123321",
    @ColumnInfo(name = DIAGNOSIS) var diagnosis: String = "",
    @ColumnInfo(name = NAME_AND_SURNAME) var nameAndSurname: String = "Пацієнт",
    @ColumnInfo(name = AGE) var age: Int = 0,
    @ColumnInfo(name = ADMISSION) var dateOfAdmission: String = "",
    @ColumnInfo(name = ACCIDENT_DATE) var accidentDate: String = "",
    @ColumnInfo(name = ACCIDENT) @TypeConverters(AccidentTypeConverter::class) var accident: Int = AccidentType.UNDEFINED.ordinal,
    @ColumnInfo(name = AO_INDEX) var AOIndex: String = "",
    @ColumnInfo(name = APP_REGISTRATION_DATE) var date: String = "",
    @ColumnInfo(name = IMAGES) @TypeConverters(ImagesListConverter::class) var images: MutableList<Photo>  = mutableListOf<Photo>()
    ): Parcelable{

    companion object {
        const val PATIENT_ID = "patientId"
        const val DIAGNOSIS = "diagnosis"
        const val NAME_AND_SURNAME = "nameAndSurname"
        const val AGE = "age"
        const val ADMISSION = "admission"
        const val ACCIDENT_DATE = "accidentDate"
        const val ACCIDENT = "accident"
        const val AO_INDEX = "AOIndex"
        const val APP_REGISTRATION_DATE = "appRegistrationDate"
        const val IMAGES = "images"
    }



    override fun toString(): String {
        return "Patient(patientId='$patientId', diagnosis='$diagnosis', nameAndSurname='$nameAndSurname', age=$age, dateOfAdmission='$dateOfAdmission', accidentDate='$accidentDate', accident=$accident, AOIndex='$AOIndex', date='$date', images=$images)"
    }


}


