package ua.roman777.traumabook.services.dataBaseService

import ua.roman777.traumabook.dataBase.PatientDB.Companion.DATABASE_NAME
import ua.roman777.traumabook.dataBase.dataEntity.Patient.Companion.PATIENT_ID


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */

const val get_by_id_query = "SELECT * FROM $DATABASE_NAME WHERE $PATIENT_ID = "