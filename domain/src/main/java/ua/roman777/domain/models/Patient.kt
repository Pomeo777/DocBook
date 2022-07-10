package ua.roman777.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.roman777.domain.models.modelEnums.AccidentType


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
@Parcelize
data class Patient(
     var patientId: String = "123321",
    var diagnosis: String = "",
    var nameAndSurname: String = "Пацієнт",
    var age: String = "",
    var dateOfAdmission: String = "",
    var accidentDate: String = "",
    var accident: Int = AccidentType.UNDEFINED.ordinal,
    var AOIndex: String = "",
    var date: String = "",
    var images: MutableList<Photo>  = mutableListOf<Photo>() ) : Parcelable {



    override fun toString(): String {
        return "Patient(patientId='$patientId', diagnosis='$diagnosis', nameAndSurname='$nameAndSurname', age=$age, dateOfAdmission='$dateOfAdmission', accidentDate='$accidentDate', accident=$accident, AOIndex='$AOIndex', date='$date', images=$images)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Patient) return false

        if (patientId != other.patientId) return false
        if (diagnosis != other.diagnosis) return false
        if (nameAndSurname != other.nameAndSurname) return false
        if (age != other.age) return false
        if (dateOfAdmission != other.dateOfAdmission) return false
        if (accidentDate != other.accidentDate) return false
        if (accident != other.accident) return false
        if (AOIndex != other.AOIndex) return false
        if (date != other.date) return false
        if (images != other.images) return false

        return true
    }

    override fun hashCode(): Int {
        var result = patientId.hashCode()
        result = 31 * result + diagnosis.hashCode()
        result = 31 * result + nameAndSurname.hashCode()
        result = 31 * result + age.hashCode()
        result = 31 * result + dateOfAdmission.hashCode()
        result = 31 * result + accidentDate.hashCode()
        result = 31 * result + accident
        result = 31 * result + AOIndex.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + images.hashCode()
        return result
    }


}


