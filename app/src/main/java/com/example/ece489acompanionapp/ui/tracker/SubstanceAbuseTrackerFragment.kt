package com.example.ece489acompanionapp.ui.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ece489acompanionapp.databinding.FragmentTrackerExerciseBinding
import com.example.ece489acompanionapp.databinding.FragmentTrackerFoodBinding
import com.example.ece489acompanionapp.databinding.FragmentTrackerMeditationBinding
import com.example.ece489acompanionapp.databinding.FragmentTrackerSleepBinding
import com.example.ece489acompanionapp.databinding.FragmentTrackerSubstanceAbuseBinding
import com.example.ece489acompanionapp.databinding.FragmentTrackerWaterBinding
import com.example.ece489acompanionapp.ui.calendar.ExerciseTrackerViewModel
import com.example.ece489acompanionapp.ui.calendar.FoodTrackerViewModel
import com.example.ece489acompanionapp.ui.calendar.MeditationTrackerViewModel
import com.example.ece489acompanionapp.ui.calendar.SleepTrackerViewModel
import com.example.ece489acompanionapp.ui.calendar.SubstanceAbuseTrackerViewModel
import com.example.ece489acompanionapp.ui.calendar.WaterTrackerViewModel

class SubstanceAbuseTrackerFragment : Fragment() {

    private var _binding: FragmentTrackerSubstanceAbuseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val substanceAbuseTrackerViewModel =
            ViewModelProvider(this).get(SubstanceAbuseTrackerViewModel::class.java)

        _binding = FragmentTrackerSubstanceAbuseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.trackerSubstanceAbuse
        substanceAbuseTrackerViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}