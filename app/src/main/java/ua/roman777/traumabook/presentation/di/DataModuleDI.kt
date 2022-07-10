package ua.roman777.traumabook.presentation.di

import org.koin.dsl.module
import ua.roman777.traumabook.data.PatientRepositoryImpl
import ua.roman777.traumabook.data.dataBase.PatientDB
import ua.roman777.traumabook.data.dataBase.RoomDBCreator
import ua.roman777.traumabook.data.dataBase.dao.PatientDao
import ua.roman777.traumabook.data.patientStorage.IPatientStorage
import ua.roman777.traumabook.data.patientStorage.RoomPatientStorageImpl


/**
 * Created by Roman Fedchenko
 * date 06.07.2022
 * author email pomeo77777@gmail.com
 */

val dataModule = module {

    single<PatientDB>{
        RoomDBCreator().getRoomDataBase(context = get())
    }

    single<PatientDao>{
        RoomDBCreator().getPatientDao(db = get())
    }

    single<IPatientStorage>{
        RoomPatientStorageImpl(dao = get())
    }


    single<ua.roman777.domain.IPatientRepository>{
        PatientRepositoryImpl(storage = get())
    }
}