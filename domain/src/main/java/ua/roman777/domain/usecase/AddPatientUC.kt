package ua.roman777.domain.usecase

import ua.roman777.domain.IPatientRepository
import ua.roman777.domain.getDate
import ua.roman777.domain.models.Patient
import ua.roman777.domain.models.Photo
import java.util.*


/**
 * Created by Roman Fedchenko
 * date 05.07.2022
 * author email pomeo77777@gmail.com
 */
class AddPatientUC(private val repository: IPatientRepository) {

    suspend fun addPatient(photoPath: String){
        val patient = createPatient(photoPath)
        repository.addPatient(patient)
    }

    private fun createPatient(photoPath: String): Patient {
        val patient = Patient()
        patient.patientId = Calendar.getInstance().timeInMillis.toString()
        patient.date = getDate()
        patient.images.add(Photo("file://+$photoPath", patient.date))
        return patient
    }

}