package ua.roman777.traumabook.data.patientStorage

import androidx.sqlite.db.SimpleSQLiteQuery
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.roman777.domain.models.Patient
import ua.roman777.traumabook.data.dataBase.dao.PatientDao
import ua.roman777.traumabook.data.dataBase.dataBaseUtils.patientDBListToPatientList
import ua.roman777.traumabook.data.dataBase.dataBaseUtils.patientDBToPatient
import ua.roman777.traumabook.data.dataBase.dataBaseUtils.patientToPatientDB


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
class RoomPatientStorageImpl(private var dao: PatientDao): IPatientStorage{


    override suspend fun addPatient(patient: Patient): Long {
       return dao.addPatient(Gson().patientToPatientDB(patient))
    }

    override suspend fun updatePatient(patient: Patient): Int {
       return dao.updatePatient(Gson().patientToPatientDB(patient))
    }

    override fun getPatient(query: String): Flow<MutableList<Patient>> {
         return dao.getWithQuery(SimpleSQLiteQuery(query)).map { value -> Gson().patientDBListToPatientList(value) }
    }

    override fun getAllFlow(): Flow<MutableList<Patient>> {
        return dao.getAllFlow().map { value -> Gson().patientDBListToPatientList(value) }
    }

    override fun getAll(): MutableList<Patient> {
        return dao.getAll().map { Gson().patientDBToPatient(it) }.toMutableList()
    }
}