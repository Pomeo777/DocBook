package ua.roman777.domain.usecase


import kotlinx.coroutines.flow.Flow
import ua.roman777.domain.IPatientRepository
import ua.roman777.domain.models.Patient


/**
 * Created by Roman Fedchenko
 * date 05.07.2022
 * author email pomeo77777@gmail.com
 */
class GetAllPatientFlowUC(private val repository: IPatientRepository) {

    fun getAllPatientFlow(): Flow<MutableList<Patient>> {
        return repository.getAllFlow()
    }
}