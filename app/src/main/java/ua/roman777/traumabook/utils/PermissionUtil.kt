package ua.roman777.traumabook.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


/**
 * Created by Roman Fedchenko
 * date 24.05.2022
 * author email pomeo77777@gmail.com
 */
class PermissionUtil {

    fun showPermissionDialog(
        permission: String,
        myPermissionCode: Int,
        activity: Activity?
    ): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        return if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    activity!!,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(activity, arrayOf(permission), myPermissionCode)
                false
            } else {
                true
            }
        } else {
            true
        }
    }

    fun showFragmentPermissionDialog(
        permission: String,
        myPermissionCode: Int,
        activity: Activity?,
        fragment: Fragment
    ): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        return if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    activity!!,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                fragment.requestPermissions(arrayOf(permission), myPermissionCode)
                false
            } else {
                true
            }
        } else {
            true
        }
    }

    fun checkPermission(permission: String?, context: Context?): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        return if (currentAPIVersion >= Build.VERSION_CODES.M) {
            ContextCompat.checkSelfPermission(
                context!!,
                permission!!
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }
}