package ua.roman777.traumabook.services.dataBaseService

import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow
import ua.roman777.traumabook.dataBase.dao.PatientDao
import ua.roman777.traumabook.dataBase.dataEntity.Patient


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
class PatientRoomRepository(private var dao: PatientDao){


    suspend fun addPatient(patient: Patient): Long {
       return dao.addPatient(patient)
    }

    suspend fun updatePatient(patient: Patient): Int {
       return dao.updatePatient(patient)
    }

    fun getPatient(query: SupportSQLiteQuery): Flow<MutableList<Patient>> {
        return dao.getWithQuery(query)
    }

    fun getAllFlow(): Flow<MutableList<Patient>> {
        return dao.getAllFlow()
    }

    fun getAll(): MutableList<Patient> {
        return dao.getAll()
    }
}