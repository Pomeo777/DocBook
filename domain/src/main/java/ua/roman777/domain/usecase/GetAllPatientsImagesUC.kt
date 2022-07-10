package ua.roman777.domain.usecase

import ua.roman777.domain.IPatientRepository
import ua.roman777.domain.models.Patient
import ua.roman777.domain.models.Photo


/**
 * Created by Roman Fedchenko
 * date 05.07.2022
 * author email pomeo77777@gmail.com
 */
class GetAllPatientsImagesUC(private val repository: IPatientRepository) {

    fun getAllPatientsImages(): MutableList<Photo> {
        val collectedPhoto = mutableListOf<Photo>()
        repository.getAll().forEach{ patient ->
            setImagesDescription(patient).let { collectedPhoto.addAll(it.images) }
        }
        return collectedPhoto
    }


    private fun setImagesDescription(patient: Patient): Patient {
        for(i in patient.images){
            i.description = patient.nameAndSurname
        }
        return patient
    }
}