package ua.roman777.domain.usecase

import ua.roman777.domain.IPatientRepository
import ua.roman777.domain.getDate
import ua.roman777.domain.models.Patient
import ua.roman777.domain.models.Photo


/**
 * Created by Roman Fedchenko
 * date 05.07.2022
 * author email pomeo77777@gmail.com
 */
class AddPatientImageUC(private val repository: IPatientRepository) {

    suspend fun addPatientPhoto(changedPatient: Patient, pathList: List<String>) {
        changedPatient.let{ p ->
            for(path in pathList){
                Photo(path, getDate() ).let { p.images.add(it) }
            }
            repository.updatePatient(changedPatient)
        }
    }

    suspend fun addPatientPhoto(changedPatient: Patient, photoPath: String) {
        changedPatient.let{ p ->
            p.images.add(Photo("file://+$photoPath", getDate()))
            repository.updatePatient(changedPatient)
        }

    }
}