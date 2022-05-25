package ua.roman777.traumabook.application

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber
import ua.roman777.traumabook.BuildConfig
import ua.roman777.traumabook.application.crashlytic.CrashReportingTree
import ua.roman777.traumabook.application.crashlytic.DebugTimberTree
import ua.roman777.traumabook.dataBase.PatientDB
import ua.roman777.traumabook.dataBase.PatientDB.Companion.DATABASE_NAME
import ua.roman777.traumabook.services.PatientRepository
import ua.roman777.traumabook.services.dataBaseService.RoomPatientRepositoryImpl


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
class TBookApplication: Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val patientDB by lazy {
        Room.databaseBuilder(applicationContext, PatientDB::class.java, DATABASE_NAME)
            .build()
    }

    private val patientDao by lazy {  patientDB.patientDao() }
    val patientRepository by lazy {  PatientRepository(RoomPatientRepositoryImpl(patientDao)) }

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTimberTree(applicationContext))
        } else {
            Timber.plant(CrashReportingTree(applicationContext))
        }
    }


}