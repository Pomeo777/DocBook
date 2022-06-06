package ua.roman777.traumabook.ui.photoViewer

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import timber.log.Timber
import ua.roman777.traumabook.dataBase.dataEntity.Photo
import ua.roman777.traumabook.databinding.FragmentPatientPhotoBinding

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class PatientPhotoFragment : Fragment(){
//    private val hideHandler = Handler(Looper.myLooper()!!)
//
//    @Suppress("InlinedApi")
//    private val hidePart2Runnable = Runnable {
//        // Delayed removal of status and navigation bar
//
//        // Note that some of these constants are new as of API 16 (Jelly Bean)
//        // and API 19 (KitKat). It is safe to use them, as they are inlined
//        // at compile-time and do nothing on earlier devices.
//        val flags =
//            View.SYSTEM_UI_FLAG_LOW_PROFILE or
//                    View.SYSTEM_UI_FLAG_FULLSCREEN or
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
//                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
//                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
//                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//        activity?.window?.decorView?.systemUiVisibility = flags
//        (activity as? AppCompatActivity)?.supportActionBar?.hide()
//    }
//
//    private var visible: Boolean = false
//    private val hideRunnable = Runnable { hide() }


    private var fullscreenContent: View? = null

    private var _binding: FragmentPatientPhotoBinding? = null
    private val binding get() = _binding!!

    private var photos: List<Photo> = mutableListOf()
    private var patientName = ""
    private var imgPosition = 0
    private val args: PatientPhotoFragmentArgs by navArgs()
    private val viewModel: PatientPhotosViewModel by viewModels{
        PatientPhotosViewModel.PatientPhotosViewModelFactory(photos)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        photos = args.photoList.toMutableList()
        patientName = args.patientName
        imgPosition = args.imgPosition
        Timber.d("onAttach(). photos = %s", photos)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPatientPhotoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = patientName
//        visible = true

        binding.vpPhoto.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        // Set up the user interaction to manually show or hide the system UI.


        viewModel.photoFragments.observe(viewLifecycleOwner) {
            Timber.d("onViewCreated(). photos = %s", it)

            binding.vpPhoto.adapter = PhotoViewPagerAdapter(this, it)
            binding.vpPhoto.offscreenPageLimit = 3
            binding.vpPhoto.postDelayed({
                binding.vpPhoto.setCurrentItem(imgPosition, false)
            }, 100)
        }
    }

//    override fun onResume() {
//        super.onResume()
//        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//
//        // Trigger the initial hide() shortly after the activity has been
//        // created, to briefly hint to the user that UI controls
//        // are available.
//        delayedHide(100)
//    }
//
//    override fun onPause() {
//        super.onPause()
//        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//
//        // Clear the systemUiVisibility flag
//        activity?.window?.decorView?.systemUiVisibility = 0
//        show()
//    }

    override fun onDestroy() {
        super.onDestroy()
        fullscreenContent = null
    }

//    private fun toggle() {
//        Timber.d("toggle()")
//        if (visible) {
//            hide()
//        } else {
//            show()
//        }
//    }

//    private fun hide() {
//        // Hide UI first
//        visible = false
//
//        // Schedule a runnable to remove the status and navigation bar after a delay
//        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY.toLong())
//    }
//
//    @Suppress("InlinedApi")
//    private fun show() {
//        // Show the system bar
//        fullscreenContent?.systemUiVisibility =
//            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
//                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//        visible = true
//
//        // Schedule a runnable to display UI elements after a delay
//        hideHandler.removeCallbacks(hidePart2Runnable)
//        (activity as? AppCompatActivity)?.supportActionBar?.show()
//    }
//
//    /**
//     * Schedules a call to hide() in [delayMillis], canceling any
//     * previously scheduled calls.
//     */
//    private fun delayedHide(delayMillis: Int) {
//        hideHandler.removeCallbacks(hideRunnable)
//        hideHandler.postDelayed(hideRunnable, delayMillis.toLong())
//    }

//    companion object {
//        /**
//         * Whether or not the system UI should be auto-hidden after
//         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
//         */
//        private const val AUTO_HIDE = true
//
//        /**
//         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
//         * user interaction before hiding the system UI.
//         */
//        private const val AUTO_HIDE_DELAY_MILLIS = 3000
//
//        /**
//         * Some older devices needs a small delay between UI widget updates
//         * and a change of the status and navigation bar.
//         */
//        private const val UI_ANIMATION_DELAY = 300
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}