package ua.roman777.traumabook.services

import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow
import ua.roman777.traumabook.dataBase.dataEntity.Patient


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
interface IPatientRepository {

    suspend fun addPatient(patient: Patient): Long
    suspend fun updatePatient(patient: Patient): Int
    fun getPatients(query: SupportSQLiteQuery): Flow<MutableList<Patient>>
    fun getAllFlow(): Flow<MutableList<Patient>>
    fun getAll(): MutableList<Patient>
}