package ua.roman777.traumabook.presentation.application

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import ua.roman777.traumabook.BuildConfig
import ua.roman777.traumabook.presentation.application.crashlytic.CrashReportingTree
import ua.roman777.traumabook.presentation.application.crashlytic.DebugTimberTree
import ua.roman777.traumabook.data.dataBase.PatientDB
import ua.roman777.traumabook.data.dataBase.PatientDB.Companion.DATABASE_NAME
import ua.roman777.traumabook.data.PatientRepositoryImpl
import ua.roman777.traumabook.presentation.di.appModule
import ua.roman777.traumabook.presentation.di.dataModule
import ua.roman777.traumabook.presentation.di.domainModule


/**
 * Created by Roman Fedchenko
 * date 23.05.2022
 * author email pomeo77777@gmail.com
 */
class TBookApplication: Application() {


    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@TBookApplication)
            modules(appModule, domainModule, dataModule )
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTimberTree(applicationContext))
        } else {
            Timber.plant(CrashReportingTree(applicationContext))
        }
    }


}