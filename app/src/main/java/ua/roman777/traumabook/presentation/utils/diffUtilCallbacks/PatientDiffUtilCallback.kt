package ua.roman777.traumabook.presentation.utils.diffUtilCallbacks

import androidx.recyclerview.widget.DiffUtil
import ua.roman777.domain.models.Patient


/**
 * Created by Roman Fedchenko
 * date 25.05.2022
 * author email pomeo77777@gmail.com
 */
class PatientDiffUtilCallback(private val oldList: MutableList<Patient>,
                              private val newList: MutableList<Patient>): DiffUtil.Callback() {


    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPatient: Patient = oldList[oldItemPosition]
        val newPatient: Patient = newList[newItemPosition]
        return oldPatient.patientId == newPatient.patientId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPatient: Patient = oldList[oldItemPosition]
        val newPatient: Patient = newList[newItemPosition]
        return oldPatient.patientId == newPatient.patientId
                && oldPatient.AOIndex == newPatient.AOIndex
                && oldPatient.diagnosis == newPatient.diagnosis
                && oldPatient.accident == newPatient.accident
                && oldPatient.nameAndSurname == newPatient.nameAndSurname
                && oldPatient.images.size == newPatient.images.size
    }
}