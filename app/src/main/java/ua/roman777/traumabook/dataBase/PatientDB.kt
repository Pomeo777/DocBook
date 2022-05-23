package ua.roman777.traumabook.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
@Database(entities = [PatientDB::class], version = 1, exportSchema = false)
abstract class PatientDB: RoomDatabase() {
}