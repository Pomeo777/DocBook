package ua.roman777.traumabook.ui.photoViewer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import timber.log.Timber
import ua.roman777.traumabook.BR
import ua.roman777.traumabook.application.PHOTO_OBJECT
import ua.roman777.traumabook.dataBase.dataEntity.Photo
import ua.roman777.traumabook.databinding.PhotoItemFragmentBinding
import ua.roman777.traumabook.utils.OnItemClickListener
import ua.roman777.traumabook.utils.setOnClickListener


/**
 * Created by Roman Fedchenko
 * date 26.05.2022
 * author email pomeo77777@gmail.com
 */
class PhotoItemFragment: Fragment() {

    private var _binding: PhotoItemFragmentBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PhotoItemFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photo = arguments?.getParcelable(PHOTO_OBJECT)?: Photo("","")
        Timber.d("onViewCreated(). photo = %s", photo)
        binding.setVariable(BR.photo, photo)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object{
        fun instance(photo: Photo): PhotoItemFragment{
            val data = Bundle()
            data.putParcelable(PHOTO_OBJECT, photo)
            return PhotoItemFragment().apply { arguments = data }
        }
    }
}