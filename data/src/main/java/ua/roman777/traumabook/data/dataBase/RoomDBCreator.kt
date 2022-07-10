package ua.roman777.traumabook.data.dataBase

import android.content.Context
import androidx.room.Room
import ua.roman777.traumabook.data.dataBase.dao.PatientDao


/**
 * Created by Roman Fedchenko
 * date 09.07.2022
 * author email pomeo77777@gmail.com
 */
class RoomDBCreator {

    fun getRoomDataBase(context: Context):PatientDB{
        return Room.databaseBuilder(context,
            PatientDB::class.java,
            PatientDB.DATABASE_NAME)
            .build()
    }

    fun getPatientDao(db: PatientDB): PatientDao{
        return db.patientDao()
    }
}