package ua.roman777.traumabook.presentation.utils.activityContracts

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract
import ua.roman777.traumabook.presentation.application.CAMERA_FILE_STRING_URI
import ua.roman777.traumabook.presentation.application.INT_RESULT
import ua.roman777.traumabook.presentation.application.PHOTO_PATH


/**
 * Created by Roman Fedchenko
 * date 27.05.2022
 * author email pomeo77777@gmail.com
 */
class AddPhotoFromCameraContract: ActivityResultContract<Intent, Intent>() {
    private lateinit var uri: Uri
    private lateinit var photoPath: String

    override fun createIntent(context: Context, input: Intent): Intent {
        uri  = Uri.parse(input.getStringExtra(CAMERA_FILE_STRING_URI))
        photoPath  = input.getStringExtra(PHOTO_PATH)!!

        return Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            .putExtra(MediaStore.EXTRA_OUTPUT, uri)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Intent {
        val myIntent  = Intent()
        myIntent.putExtra(INT_RESULT, resultCode)
        myIntent.putExtra(CAMERA_FILE_STRING_URI, uri.toString())
        myIntent.putExtra(PHOTO_PATH, photoPath)
        return myIntent
    }
}