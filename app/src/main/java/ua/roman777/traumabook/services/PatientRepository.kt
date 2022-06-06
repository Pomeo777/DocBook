package ua.roman777.traumabook.services

import kotlinx.coroutines.flow.Flow
import ua.roman777.traumabook.dataBase.dataEntity.Patient


/**
 * Created by Roman Fedchenko
 * date 24.05.2022
 * author email pomeo77777@gmail.com
 */
class PatientRepository(private val repository: IPatientRepository) {

    fun getAllPatientFlow(): Flow<MutableList<Patient>>{
        return repository.getAllFlow()
    }

    fun getAllPatient(): MutableList<Patient>{
        return repository.getAll()
    }

    suspend fun addNewUser(patient: Patient): Long{
        return repository.addPatient(patient)
    }

    suspend fun updateUser(patient: Patient): Int{
        return repository.updatePatient(patient)
    }



    //todo sync with server data
}