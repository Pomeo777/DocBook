package ua.roman777.traumabook.data.dataBase.dao

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow
import ua.roman777.traumabook.data.patientStorage.PatientDBModel


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
@Dao
interface  PatientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPatient(patient: PatientDBModel): Long

    @Update
    fun updatePatient(patient: PatientDBModel): Int

    @RawQuery(observedEntities = [PatientDBModel::class])
    fun getWithQuery(query: SupportSQLiteQuery): Flow<MutableList<PatientDBModel>>

    @Query("SELECT * FROM patients")
    fun getAllFlow(): Flow<MutableList<PatientDBModel>>

    @Query("SELECT * FROM patients")
    fun getAll(): MutableList<PatientDBModel>

}