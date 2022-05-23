package ua.roman777.traumabook.dataBase.dataEntity

import androidx.room.*
import ua.roman777.traumabook.dataBase.utils.AccidentTypeConverter
import ua.roman777.traumabook.dataBase.utils.ImagesListConverter


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
@Entity(tableName = "patients")
data class Patient(
    @PrimaryKey @ColumnInfo(name = "patientId") val patientId: String,
    @ColumnInfo(name = "diagnosis") val diagnosis: String,
    @ColumnInfo(name = "nameAndSurname") val nameAndSurname: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "admission") val dateOfAdmission: String,
    @ColumnInfo(name = "accidentDate") val accidentDate: String,
    @ColumnInfo(name = "accident") @TypeConverters(AccidentTypeConverter::class) val accident: Int,
    @ColumnInfo(name = "AOIndex") val AOIndex: String,
    @ColumnInfo(name = "appRegistrationDate") val date: String,
    @ColumnInfo(name = "images") @TypeConverters(ImagesListConverter::class) val images: String
    )
