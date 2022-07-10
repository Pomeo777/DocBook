package ua.roman777.traumabook.data

import kotlinx.coroutines.flow.Flow
import ua.roman777.domain.models.Patient
import ua.roman777.traumabook.data.patientStorage.IPatientStorage


/**
 * Created by Roman Fedchenko
 * date 24.05.2022
 * author email pomeo77777@gmail.com
 */
class PatientRepositoryImpl(private val storage: IPatientStorage):
    ua.roman777.domain.IPatientRepository {

    override suspend fun addPatient(patient: Patient): Long {
        return storage.addPatient(patient)
    }

    override suspend fun updatePatient(patient: Patient): Int {
        return storage.updatePatient(patient)
    }

    override fun getAllFlow(): Flow<MutableList<Patient>> {
        return storage.getAllFlow()
    }

    override fun getAll(): MutableList<Patient> {
        return storage.getAll()
    }
}