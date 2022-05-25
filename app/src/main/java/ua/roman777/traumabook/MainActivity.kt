package ua.roman777.traumabook

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch
import timber.log.Timber
import ua.roman777.traumabook.application.TBookApplication
import ua.roman777.traumabook.dataBase.dataEntity.Patient
import ua.roman777.traumabook.databinding.ActivityMainBinding
import ua.roman777.traumabook.services.photoService.MakePhotoModel
import ua.roman777.traumabook.utils.PermissionUtil
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val makePhotoModel by lazy { MakePhotoModel() }

    private  val takeImageResult = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
        Timber.d("registerForActivityResult().")
        if (isSuccess) {
            lifecycleScope.launch {
                val patient = createPatient(makePhotoModel.cameraImagePath)
                Timber.d("registerForActivityResult(). new patient: %s", patient.toString())
                (application as TBookApplication).patientRepository.addNewUser(patient)
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            createNewPatient()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        hideSplashScreen()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == MY_PERMISSIONS_REQUEST_WORK_WITH_CAMERA){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                takeImage()
            }
        }
    }

    private fun createNewPatient() {

        run {
            val permissionUtil = PermissionUtil()
            if (permissionUtil.showPermissionDialog(
                    Manifest.permission.CAMERA,
                    MY_PERMISSIONS_REQUEST_WORK_WITH_CAMERA,
                    this)){
                takeImage()
            }

        }
    }

    private fun createPatient(photoPath: String): Patient{
        Timber.d("createPatient(). photoPath: %s", photoPath)
        val patient = Patient()
        patient.patientId = Calendar.getInstance().timeInMillis.toString()
        patient.images.add("file://+$photoPath")
        patient.date = getDate()
        return patient
    }

    private fun takeImage() {
        lifecycleScope.launchWhenStarted {
            makePhotoModel.getFromCamera(this@MainActivity).let { uri ->
                takeImageResult.launch(uri)
            }
        }
    }

    private fun getDate(): String{
        val comDate: Date = Calendar.getInstance().time
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(comDate)
    }

    private fun hideSplashScreen() {
        Timer().schedule(1500){
            binding.ivLogoSplashView.visibility = View.GONE
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



    companion object{
        const val MY_PERMISSIONS_REQUEST_WORK_WITH_CAMERA = 3442
    }
}