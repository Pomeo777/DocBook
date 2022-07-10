package ua.roman777.traumabook.data.patientStorage

import kotlinx.coroutines.flow.Flow
import ua.roman777.domain.models.Patient


/**
 * Created by Roman Fedchenko
 * date 05.07.2022
 * author email pomeo77777@gmail.com
 */
interface IPatientStorage {
    suspend fun addPatient(patient: Patient): Long
    suspend fun updatePatient(patient: Patient): Int
    fun getPatient(query: String): Flow<MutableList<Patient>>
    fun getAllFlow(): Flow<MutableList<Patient>>
    fun getAll(): MutableList<Patient>
}