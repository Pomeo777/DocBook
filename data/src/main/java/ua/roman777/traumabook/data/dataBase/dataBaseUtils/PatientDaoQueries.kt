package ua.roman777.traumabook.data.dataBase.dataBaseUtils

import ua.roman777.traumabook.data.dataBase.PatientDB.Companion.DATABASE_NAME
import ua.roman777.traumabook.data.patientStorage.PatientDBModel.Companion.PATIENT_ID


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */

const val get_by_id_query = "SELECT * FROM $DATABASE_NAME WHERE $PATIENT_ID = "