package com.example.ece489acompanionapp.ui.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ece489acompanionapp.R
import com.example.ece489acompanionapp.databinding.FragmentTrackerBinding

class TrackerFragment : Fragment() {

    private var _binding: FragmentTrackerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val trackerViewModel =
                ViewModelProvider(this).get(TrackerViewModel::class.java)

        _binding = FragmentTrackerBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            waterTrackerButton.setOnClickListener { goToWaterTrackerScreen() }
        }

        binding?.apply {
            sleepTrackerButton.setOnClickListener { goToSleepTrackerScreen() }
        }

        binding?.apply {
            meditationTrackerButton.setOnClickListener { goToMeditationTrackerScreen() }
        }

        binding?.apply {
            exerciseTrackerButton.setOnClickListener { goToExerciseTrackerScreen() }
        }

        binding?.apply {
            foodTrackerButton.setOnClickListener { goToFoodTrackerScreen() }
        }

        binding?.apply {
            substanceAbuseTrackerButton.setOnClickListener { goToSubstanceAbuseTrackerScreen() }
        }
    }

    fun goToWaterTrackerScreen() {
        findNavController().navigate(R.id.action_navigation_tracker_to_tracker_water)
    }

    fun goToSleepTrackerScreen() {
        findNavController().navigate(R.id.action_navigation_tracker_to_tracker_sleep)
    }

    fun goToMeditationTrackerScreen() {
        findNavController().navigate(R.id.action_navigation_tracker_to_tracker_meditation)
    }

    fun goToExerciseTrackerScreen() {
        findNavController().navigate(R.id.action_navigation_tracker_to_tracker_exercise)
    }

    fun goToFoodTrackerScreen() {
        findNavController().navigate(R.id.action_navigation_tracker_to_tracker_food)
    }

    fun goToSubstanceAbuseTrackerScreen() {
        findNavController().navigate(R.id.action_navigation_tracker_to_tracker_substance_abuse)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}