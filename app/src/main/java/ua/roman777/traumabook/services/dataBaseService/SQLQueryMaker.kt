package ua.roman777.traumabook.services.dataBaseService

import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import ua.roman777.traumabook.dataBase.dao.PatientDao


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
class SQLQueryMaker {

    fun getById(patientId: String) =  SimpleSQLiteQuery(get_by_id_query + patientId)
}