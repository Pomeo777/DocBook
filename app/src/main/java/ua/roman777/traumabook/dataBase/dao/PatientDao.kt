package ua.roman777.traumabook.dataBase.dao

import android.database.sqlite.SQLiteQuery
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow
import ua.roman777.traumabook.dataBase.dataEntity.Patient


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
interface  PatientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPatient(patient: Patient)

    @Update
    suspend fun updatePatient(patient: Patient)

    @RawQuery
    fun getWithQuery(query: SupportSQLiteQuery): Flow<MutableList<Patient>>

    @Query("SELECT * FROM patients")
    fun getAll(query: SupportSQLiteQuery): Flow<MutableList<Patient>>

}