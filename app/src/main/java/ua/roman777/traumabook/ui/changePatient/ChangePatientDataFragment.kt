package ua.roman777.traumabook.ui.changePatient

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
import timber.log.Timber
import ua.roman777.traumabook.R
import ua.roman777.traumabook.application.TBookApplication
import ua.roman777.traumabook.dataBase.dataEntity.Patient
import ua.roman777.traumabook.databinding.FragmentChangePatientDataBinding
import ua.roman777.traumabook.utils.setOnClickListener
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ChangePatientDataFragment : Fragment() {


    private  val viewModel: ChangePatientDataViewModel by viewModels{
        ChangePatientDataViewModel
            .ChangePatientDataViewModelFactory((requireActivity().application as TBookApplication).patientRepository)
    }
    private var _binding: FragmentChangePatientDataBinding? = null
    private val binding get() = _binding!!

    private lateinit var patient: Patient
    private lateinit var tempPatient: Patient
    private val args: ChangePatientDataFragmentArgs by navArgs()


    private var adDL =
        OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            val cal = Calendar.getInstance()
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val newDate = getDate(cal.timeInMillis)
            binding.tilAdmissionDate.idEt.setText(newDate)
            tempPatient.dateOfAdmission = newDate
            updateLiveDataPatient()
        }

    private var acDL =
        OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            val cal = Calendar.getInstance()
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val newDate = getDate(cal.timeInMillis)
            binding.tilAccidentDate.idEt.setText(newDate)
            tempPatient.accidentDate = newDate
            updateLiveDataPatient()
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        patient = args.patient
        tempPatient = patient.copy()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChangePatientDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.edit_patient_view_title)

        viewModel.patientLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Timber.d("onViewCreated(). observe/ patient: %s", it)
            Timber.d("onViewCreated(). observe/ patient == it: %s", it.equals(patient))
            binding.btnSaveChanges.isEnabled = !it.equals(patient)
            binding.btnSaveChanges.isChecked = !it.equals(patient)
        })

        viewModel.updateUserData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            findNavController().popBackStack()
        })

        setPatientData()
        binding.btnSaveChanges.setOnClickListener { viewModel.updatePatient() }
    }

    private fun setPatientData() {
        viewModel.patientLiveData.value = patient
        binding.tilName.sTil.hint = getString(R.string.patient_name_hint)
        binding.tilName.idEt.setText(patient.nameAndSurname)
        binding.tilName.idEt.doOnTextChanged { text, _, _, _ -> text?.let {
            if(text.trim().toString().isEmpty()) tempPatient.nameAndSurname = getString(R.string.patient)
            else  tempPatient.nameAndSurname = text.toString()
            updateLiveDataPatient()
        }
        }

        binding.tilAge.sTil.hint = getString(R.string.patient_age_hint)
        binding.tilAge.idEt.setText(patient.age)
        binding.tilAge.idEt.doOnTextChanged { text, _, _, _ -> text?.let {
            if(text.trim().toString().isEmpty()) tempPatient.age = ""
            else  tempPatient.age = text.toString()
            updateLiveDataPatient()
        }
        }

        binding.tilAOIndex.sTil.hint = getString(R.string.patient_AOIndex_hint)
        binding.tilAOIndex.idEt.setText(patient.AOIndex)
        binding.tilAOIndex.idEt.doOnTextChanged { text, _, _, _ -> text?.let {
            if(text.trim().toString().isEmpty()) tempPatient.AOIndex = ""
            else  tempPatient.AOIndex = text.toString()
            updateLiveDataPatient()
        }
        }

        binding.tilDiagnosis.mTil.isHintEnabled = true
        binding.tilDiagnosis.mTil.hint = getString(R.string.diagnosis)
        binding.tilDiagnosis.idEt.setText(patient.diagnosis)
        binding.tilDiagnosis.idEt.doOnTextChanged { text, _, _, _ -> text?.let {
            if(text.trim().toString().isEmpty()) tempPatient.diagnosis = ""
            else  tempPatient.diagnosis = text.toString()
            updateLiveDataPatient()
        }
        }

        setAccidentDate()
        setAdmissionDate()

    }

    private fun updateLiveDataPatient(){
        viewModel.patientLiveData.postValue(tempPatient)
    }

    private fun setAccidentDate() {
        binding.tilAccidentDate.sTil.endIconMode = END_ICON_NONE
        binding.tilAccidentDate.idEt.isFocusableInTouchMode = false
        binding.tilAccidentDate.sTil.hint = getString(R.string.patient_accidentDate_hint)
        binding.tilAccidentDate.idEt.setText(patient.accidentDate)



        binding.tilAccidentDate.idEt.setOnClickListener(300){
            DatePickerDialog(
                requireContext(),
                acDL,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
                .show()
        }
    }

    private fun setAdmissionDate() {
        binding.tilAdmissionDate.sTil.endIconMode = END_ICON_NONE
        binding.tilAdmissionDate.idEt.isFocusableInTouchMode = false
        binding.tilAdmissionDate.sTil.hint = getString(R.string.patient_admissionDate_hint)
        if(patient.dateOfAdmission.isEmpty()){
            binding.tilAdmissionDate.idEt.setText(
                DateUtils.formatDateTime(
                    requireContext(),
                    Calendar.getInstance().timeInMillis,
                    DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
                )
            )
        }else{
            binding.tilAdmissionDate.idEt.setText(patient.dateOfAdmission)
        }


        binding.tilAdmissionDate.idEt.setOnClickListener(300){
            DatePickerDialog(
                requireContext(),
                adDL,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
                .show()
        }
    }

    private fun getDate(comDate: Long): String{
        return DateUtils.formatDateTime(
            requireContext(),
            comDate,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
        )
    }


}