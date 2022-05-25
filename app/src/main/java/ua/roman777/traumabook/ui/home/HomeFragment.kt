package ua.roman777.traumabook.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber
import ua.roman777.traumabook.BR
import ua.roman777.traumabook.R
import ua.roman777.traumabook.application.TBookApplication
import ua.roman777.traumabook.dataBase.dataEntity.Patient
import ua.roman777.traumabook.databinding.FragmentHomeBinding
import ua.roman777.traumabook.services.PatientRepository
import ua.roman777.traumabook.services.dataBaseService.RoomPatientRepositoryImpl
import ua.roman777.traumabook.utils.RecyclerBindingAdapter
import java.util.*

class HomeFragment : Fragment() {

    private  val homeViewModel: HomeViewModel by viewModels {
        HomeViewModel
            .HomeViewModelFactory(
                (requireActivity().application as TBookApplication).patientRepository)
    }
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

       homeViewModel._patients.observe(viewLifecycleOwner) { patients ->
           patients?.let {
               Timber.d("FragmentHome%s", it.toString())
                binding.recyclerView.adapter = RecyclerBindingAdapter<Patient>(R.layout.patient_item_view, BR.patient, it)
           }
       }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}