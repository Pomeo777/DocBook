package ua.roman777.traumabook.presentation.di

import org.koin.dsl.module
import ua.roman777.domain.usecase.*


/**
 * Created by Roman Fedchenko
 * date 06.07.2022
 * author email pomeo77777@gmail.com
 */

val domainModule = module {

    factory<AddPatientImageUC> {
        AddPatientImageUC(repository = get())
    }

    factory<AddPatientUC> {
        AddPatientUC(repository = get())
    }

    factory<GetAllPatientFlowUC> {
        GetAllPatientFlowUC(repository = get())
    }

    factory<GetAllPatientsImagesUC> {
        GetAllPatientsImagesUC(repository = get())
    }

    factory<EditPatientUC> {
        EditPatientUC(repository = get())
    }

}