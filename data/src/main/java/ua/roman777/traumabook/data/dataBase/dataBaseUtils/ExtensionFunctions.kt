package ua.roman777.traumabook.data.dataBase.dataBaseUtils

import com.google.gson.Gson
import timber.log.Timber
import ua.roman777.domain.models.Patient
import ua.roman777.domain.models.Photo
import ua.roman777.traumabook.data.patientStorage.PatientDBModel


/**
 * Created by Roman Fedchenko
 * date 06.07.2022
 * author email pomeo77777@gmail.com
 */
fun Gson.stringToPhotoObject(string: String): Photo {
    Timber.d("Gson. stringToPhotoObject(). string: %s", string)
    return Gson().fromJson<Photo>(string, Photo::class.java)
}

fun Gson.photoObjectToString(photo: Photo): String{
    return Gson().toJson(photo)
}

fun Gson.patientToPatientDB(patient: Patient): PatientDBModel{
    Timber.d("Gson. patientToPatientDB(). patient: %s", patient.toString())
    val patientStr = Gson().toJson(patient)
    return Gson().fromJson(patientStr, PatientDBModel::class.java)
}

fun Gson.patientDBToPatient(patientDB: PatientDBModel): Patient{
    Timber.d("Gson. patientDBToPatient(). patientDB: %s", patientDB.toString())
    val patientDBStr = Gson().toJson(patientDB)
    return Gson().fromJson(patientDBStr, Patient::class.java)
}

fun Gson.patientDBListToPatientList(patientDBList: MutableList<PatientDBModel>): MutableList<Patient>{
    Timber.d("Gson. patientDBToPatient(). patientDB: %s", patientDBList.toString())
    return patientDBList.map { Gson().patientDBToPatient(it) }.toMutableList()
}