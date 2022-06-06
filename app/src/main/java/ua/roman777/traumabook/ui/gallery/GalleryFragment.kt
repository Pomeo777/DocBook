package ua.roman777.traumabook.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import timber.log.Timber
import ua.roman777.traumabook.BR
import ua.roman777.traumabook.R
import ua.roman777.traumabook.application.TBookApplication
import ua.roman777.traumabook.dataBase.dataEntity.Patient
import ua.roman777.traumabook.dataBase.dataEntity.Photo
import ua.roman777.traumabook.databinding.FragmentGalleryBinding
import ua.roman777.traumabook.enums.PatientListItemElement
import ua.roman777.traumabook.ui.home.HomeFragmentDirections
import ua.roman777.traumabook.ui.home.HomeViewModel
import ua.roman777.traumabook.utils.GridSpacingItemDecoration
import ua.roman777.traumabook.utils.OnItemClickListener
import ua.roman777.traumabook.utils.RecyclerBindingAdapter

class GalleryFragment : Fragment() {

    var adapter: RecyclerBindingAdapter<Photo>? = null

    private val galleryViewModel: GalleryViewModel by viewModels {
        GalleryViewModel.GalleryViewModelFactory((requireActivity().application as TBookApplication).patientRepository)
    }
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.rvPortfolio.layoutManager = setGridlayoutManager()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        galleryViewModel.photos.observe(viewLifecycleOwner) {
            Timber.d("onViewCreated(). list: %s", it.toString())
            adapter =
                RecyclerBindingAdapter(
                    R.layout.gallery_photo_item_view,
                    BR.photo,
                    it.toMutableList()
                )
            binding.rvPortfolio.adapter = adapter
            setItemClickListener()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setGridlayoutManager(): GridLayoutManager {
        val spanCount = 4 // 5 columns

        val spacing = 16 // 50px

        val includeEdge = true
        binding.rvPortfolio.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        return GridLayoutManager(context, spanCount)
    }

    private fun setItemClickListener(){
        adapter!!.setOnItemClickListener(object : OnItemClickListener<Photo> {
            override fun onItemClick(item: Photo, position: Int, element: String) {
                when (element) {
                    PatientListItemElement.EDIT.value -> {
                    }
                    PatientListItemElement.SHOW_PHOTO.value -> {
                        openPhotoViewer(galleryViewModel.photos.value!!, position)
                    }
                    PatientListItemElement.SHOW_PATIENT_INFO.value -> {
                    }
                    PatientListItemElement.EDIT_PHOTO_LIST.value -> {
                    }
                }
            }
        })
    }

    private fun openPhotoViewer(items: List<Photo>, position: Int) {
        val action = GalleryFragmentDirections
            .actionNavGalleryToPatientPhotoFragment(items.toTypedArray(),
                items[position].description,
                position)
        findNavController().navigate(action)
    }
}