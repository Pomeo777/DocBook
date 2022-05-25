package ua.roman777.traumabook.services.photoService

import android.content.Context
import android.net.Uri
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.FileProvider
import timber.log.Timber
import ua.roman777.traumabook.BuildConfig
import java.io.File


/**
 * Created by Roman Fedchenko
 * date 24.05.2022
 * author email pomeo77777@gmail.com
 */
class MakePhotoModel {

    private var _cameraImagePath: String = ""
    val cameraImagePath get() = _cameraImagePath





    fun getFromCamera(context: Context): Uri {
        val photoUtil = UploadPhotoUtil()
        var file: File
        return try {
            file = photoUtil.createImageFile(context)!!
            _cameraImagePath = file.absolutePath
            Timber.d("getFromCamera(). _cameraImagePath: %s",_cameraImagePath)
            Timber.d("getFromCamera(). cameraImagePath: %s",cameraImagePath)
            FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", file)

        } catch (e: Exception) {
            Timber.e(e)
            file = photoUtil.createCacheImageFile(context)!!
            _cameraImagePath = file.absolutePath
            Timber.d("getFromCamera(). _cameraImagePath: %s", _cameraImagePath)
            Timber.d("getFromCamera(). cameraImagePath: %s", cameraImagePath)
            FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", file)

        }
    }
}