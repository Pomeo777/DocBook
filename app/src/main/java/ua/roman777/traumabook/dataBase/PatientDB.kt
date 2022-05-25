package ua.roman777.traumabook.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ua.roman777.traumabook.dataBase.PatientDB.Companion.DATABASE_VERSION
import ua.roman777.traumabook.dataBase.dao.PatientDao
import ua.roman777.traumabook.dataBase.dataEntity.Patient
import ua.roman777.traumabook.dataBase.utils.AccidentTypeConverter
import ua.roman777.traumabook.dataBase.utils.ImagesListConverter


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
@Database(entities = [Patient::class], version = DATABASE_VERSION, exportSchema = false)
@TypeConverters(AccidentTypeConverter::class, ImagesListConverter::class)
abstract class PatientDB: RoomDatabase() {

    abstract fun patientDao(): PatientDao

    companion object{
        const val DATABASE_NAME = "patients"
        const val DATABASE_VERSION = 1
    }
}

