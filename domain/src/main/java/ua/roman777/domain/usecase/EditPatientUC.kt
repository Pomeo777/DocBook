package ua.roman777.domain.usecase

import ua.roman777.domain.IPatientRepository
import ua.roman777.domain.models.Patient


/**
 * Created by Roman Fedchenko
 * date 05.07.2022
 * author email pomeo77777@gmail.com
 */
class EditPatientUC(private val repository: IPatientRepository) {

    suspend fun editPatient(patient: Patient): Int{
        return repository.updatePatient(patient)
    }
}