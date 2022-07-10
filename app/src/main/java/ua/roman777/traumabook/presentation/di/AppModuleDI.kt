package ua.roman777.traumabook.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.roman777.traumabook.presentation.ui.editPatient.EditPatientDataViewModel
import ua.roman777.traumabook.presentation.ui.gallery.GalleryViewModel
import ua.roman777.traumabook.presentation.ui.home.HomeViewModel
import ua.roman777.traumabook.presentation.ui.photoViewer.PatientPhotosViewModel


/**
 * Created by Roman Fedchenko
 * date 06.07.2022
 * author email pomeo77777@gmail.com
 */
val appModule = module {

    viewModel<HomeViewModel>{
        HomeViewModel(addPatientUC = get(),
        getAllPatientFlowUC = get(),
            addPatientImageUC = get())
    }

    viewModel<EditPatientDataViewModel>{
        EditPatientDataViewModel(updatePatientUC = get())
    }

    viewModel<GalleryViewModel>{
        GalleryViewModel(getAllPatientsImagesUC = get())
    }

    viewModel<PatientPhotosViewModel> { parameters ->
        PatientPhotosViewModel(photos = parameters.get())
    }
}