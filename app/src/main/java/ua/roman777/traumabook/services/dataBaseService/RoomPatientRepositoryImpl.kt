package ua.roman777.traumabook.services.dataBaseService

import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow
import ua.roman777.traumabook.dataBase.dao.PatientDao
import ua.roman777.traumabook.dataBase.dataEntity.Patient
import ua.roman777.traumabook.services.IPatientRepository


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
class RoomPatientRepositoryImpl(private val dao: PatientDao): IPatientRepository {
    private val repository by lazy { PatientRoomRepository(dao) }

    override suspend fun addPatient(patient: Patient): Long {
        return repository.addPatient(patient)
    }

    override suspend fun updatePatient(patient: Patient): Int {
        return repository.updatePatient(patient)
    }

    override fun getPatients(query: SupportSQLiteQuery): Flow<MutableList<Patient>> {
        return repository.getPatient(query)
    }

    override fun getAll(): Flow<MutableList<Patient>> {
       return repository.getAll()
    }
}