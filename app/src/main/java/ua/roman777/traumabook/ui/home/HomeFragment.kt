package ua.roman777.traumabook.ui.home

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import timber.log.Timber
import ua.roman777.traumabook.BR
import ua.roman777.traumabook.R
import ua.roman777.traumabook.application.*
import ua.roman777.traumabook.dataBase.dataEntity.Patient
import ua.roman777.traumabook.dataBase.dataEntity.Photo
import ua.roman777.traumabook.databinding.FragmentHomeBinding
import ua.roman777.traumabook.enums.PatientListItemElement
import ua.roman777.traumabook.services.photoService.MakePhotoModel
import ua.roman777.traumabook.utils.ImageFilePathDeterminer
import ua.roman777.traumabook.utils.OnItemClickListener
import ua.roman777.traumabook.utils.RecyclerBindingAdapter
import ua.roman777.traumabook.utils.activityContracts.AddPhotoFromCameraContract
import ua.roman777.traumabook.utils.diffUtilCallbacks.PatientDiffUtilCallback
import ua.roman777.traumabook.utils.setOnClickListener
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    private  val homeViewModel: HomeViewModel by viewModels {
        HomeViewModel
            .HomeViewModelFactory(
                (requireActivity().application as TBookApplication).patientRepository)
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    var adapter: RecyclerBindingAdapter<Patient>? = null
    private val makePhotoModel by lazy { MakePhotoModel() }


    private val cameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){ granted ->
            when{
                granted -> {
                    addFromCamera()
                }
                !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    // todo permission denied, user have noted "Don't ask again".
                }
                else ->{
                    // todo "permission denied"
                }
            }
        }

    private val addNewCameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){ granted ->
            when{
                granted -> {
                    createNewPatient()
                }
                !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    // todo permission denied, user have noted "Don't ask again".
                }
                else ->{
                    // todo "permission denied"
                }
            }
        }

    private val galleryPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){ granted ->
            when{
                granted -> {
                    addFromGallery()
                }
                !shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    // todo permission denied, user have noted "Don't ask again".
                }
                else ->{
                    // todo "permission denied"
                }
            }
        }

    private val getGalleryImageContracts =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { data ->
        Timber.d("getGalleryImageContracts. registerForActivityResult().")
        data?.let{uris ->
            lifecycleScope.launch {
                val determiner = ImageFilePathDeterminer()
                val pathList = mutableListOf<String>()
                for(uri in uris){
                    determiner.getPath(requireContext(), uri)?.let { pathList.add(it) }
                }
                homeViewModel.updatePatientPhoto(pathList.toList())
            }
        }
    }

    private val takeCameraImageContracts =
        registerForActivityResult(AddPhotoFromCameraContract()) { data ->
        Timber.d("takeCameraImageContracts. registerForActivityResult().")
        data?.let{ it ->
            Timber.d("takeCameraImageContracts. registerForActivityResult(). intent != null")
            if(it.getIntExtra(INT_RESULT, Activity.RESULT_CANCELED) == Activity.RESULT_OK){
                Timber.d("takeCameraImageContracts. registerForActivityResult(). result = Activity.RESULT_OK")
                val photoPath = data.getStringExtra(PHOTO_PATH)?: ""
                Timber.d("takeCameraImageContracts. registerForActivityResult(). photoPath = %s", photoPath)
                homeViewModel.updatePatientPhoto(photoPath)
            }
        }
    }

    private  val createNewPatientContract =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            Timber.d("registerForActivityResult().")
            if (isSuccess) {
                lifecycleScope.launch {
                    homeViewModel.addPatient(makePhotoModel.cameraImagePath)
                }
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel._patients.observe(viewLifecycleOwner) { patients ->
            patients?.let {
                Timber.d("FragmentHome%s", it.toString())
                if(adapter == null){
                    Timber.d("FragmentHome. adapter == null")
                    adapter = RecyclerBindingAdapter<Patient>(R.layout.patient_item_view, BR.patient, it.toMutableList())
                    setItemClickListener()
                    binding.recyclerView.adapter = adapter
                }else{
                    Timber.d("FragmentHome. adapter != null")
                    Timber.d("FragmentHome. adapter!!.items hash: %s", System.identityHashCode(adapter!!.items))
                    Timber.d("FragmentHome. it hash: %s", System.identityHashCode(it))
                    adapter!!.update(it, PatientDiffUtilCallback(adapter!!.items, it))
                    binding.recyclerView.adapter = adapter
                    Timber.d("FragmentHome. rvItems: %s", binding.recyclerView.adapter!!.itemCount.toString())

                }


            }
        }

        binding.fab.setOnClickListener(300) { _ ->
            addNewCameraPermission.launch(Manifest.permission.CAMERA)
        }
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setItemClickListener(){
        adapter!!.setOnItemClickListener(object : OnItemClickListener<Patient>{
            override fun onItemClick(item: Patient, element: String) {
                when (element) {
                    PatientListItemElement.EDIT.value -> {
                        //todo open info fragment
                    }
                    PatientListItemElement.SHOW_PHOTO.value -> {
                        openPatientPhotoViewer(item.images, item.nameAndSurname)
                    }
                    PatientListItemElement.SHOW_PATIENT_INFO.value -> {
                        //todo open info fragment
                    }
                    PatientListItemElement.EDIT_PHOTO_LIST.value -> {
                        addNewPhotoDialog(item)
                    }
                }
            }


        })
    }

    private fun addNewPhotoDialog(item: Patient) {
        homeViewModel.changedPatient.postValue(item)
        val dialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        dialog.setMessage(getString(R.string.add_new_photo_dialog))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.add_photo_camera)) { _, _ -> cameraPermission.launch(Manifest.permission.CAMERA) }
            .setNegativeButton(getString(R.string.add_photo_gallery)){ _, _ -> galleryPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE) }
        dialog.setCancelable(true)
        val alert: AlertDialog = dialog.create()
        alert.show()
    }

    private fun addFromGallery() {
        getGalleryImageContracts.launch("image/*")
    }

    private fun addFromCamera(){
        lifecycleScope.launchWhenStarted {
            makePhotoModel.getFromCamera(requireContext()).let { uri ->
                val intent = Intent()
                intent.putExtra(CAMERA_FILE_STRING_URI, uri.toString())
                intent.putExtra(PHOTO_PATH, makePhotoModel.cameraImagePath)
                takeCameraImageContracts.launch(intent)
            }
        }
    }



    private fun createNewPatient() {
        lifecycleScope.launchWhenStarted {
            makePhotoModel.getFromCamera(requireContext()).let { uri ->
                createNewPatientContract.launch(uri)
            }
        }
    }

    private fun openPatientPhotoViewer(list: MutableList<Photo>, patientName: String) {
        val action = HomeFragmentDirections
            .actionNavHomeToPatientPhotoFragment(list.toTypedArray(), patientName)
        findNavController().navigate(action)
    }


}