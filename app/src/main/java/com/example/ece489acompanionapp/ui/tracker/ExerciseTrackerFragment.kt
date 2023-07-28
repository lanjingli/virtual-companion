package com.example.ece489acompanionapp.ui.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ece489acompanionapp.R
import com.example.ece489acompanionapp.databinding.FragmentTrackerExerciseBinding

class ExerciseTrackerFragment : Fragment() {
    private val sharedViewModel: TrackerViewModel by activityViewModels()
    private var _binding: FragmentTrackerExerciseBinding? = null

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

        _binding = FragmentTrackerExerciseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
        }

        binding?.apply {
            exercise1.setOnClickListener {
                val isFull = viewModel?.getExerciseState(0)
                var tmp = isFull
                tmp = if (isFull == true) {
                    exercise1.setImageResource(R.drawable.ic_exercise_full_48dp)
                    false
                } else {
                    exercise1.setImageResource(R.drawable.ic_exercise_48dp)
                    true
                }
                viewModel?.setExerciseIsFull(0, tmp)
            }

            exercise2.setOnClickListener {
                val isFull = viewModel?.getExerciseState(1)
                var tmp = if (isFull == true) {
                    exercise2.setImageResource(R.drawable.ic_exercise_full_48dp)
                    false
                } else {
                    exercise2.setImageResource(R.drawable.ic_exercise_48dp)
                    true
                }
                viewModel?.setExerciseIsFull(1, tmp)
            }

            exercise3.setOnClickListener {
                val isFull = viewModel?.getExerciseState(2)
                var tmp = if (isFull == true) {
                    exercise3.setImageResource(R.drawable.ic_exercise_full_48dp)
                    false
                } else {
                    exercise3.setImageResource(R.drawable.ic_exercise_48dp)
                    true
                }
                viewModel?.setExerciseIsFull(2, tmp)
            }

            exercise4.setOnClickListener {
                val isFull = viewModel?.getExerciseState(3)
                var tmp = if (isFull == true) {
                    exercise4.setImageResource(R.drawable.ic_exercise_full_48dp)
                    false
                } else {
                    exercise4.setImageResource(R.drawable.ic_exercise_48dp)
                    true
                }
                viewModel?.setExerciseIsFull(3, tmp)
            }

            exercise5.setOnClickListener {
                val isFull = viewModel?.getExerciseState(4)
                var tmp = if (isFull == true) {
                    exercise5.setImageResource(R.drawable.ic_exercise_full_48dp)
                    false
                } else {
                    exercise5.setImageResource(R.drawable.ic_exercise_48dp)
                    true
                }
                viewModel?.setExerciseIsFull(4, tmp)
            }

            exercise6.setOnClickListener {
                val isFull = viewModel?.getExerciseState(5)
                var tmp = if (isFull == true) {
                    exercise6.setImageResource(R.drawable.ic_exercise_full_48dp)
                    false
                } else {
                    exercise6.setImageResource(R.drawable.ic_exercise_48dp)
                    true
                }
                viewModel?.setExerciseIsFull(5, tmp)
            }

            exercise7.setOnClickListener {
                val isFull = viewModel?.getExerciseState(6)
                var tmp = if (isFull == true) {
                    exercise7.setImageResource(R.drawable.ic_exercise_full_48dp)
                    false
                } else {
                    exercise7.setImageResource(R.drawable.ic_exercise_48dp)
                    true
                }
                viewModel?.setExerciseIsFull(6, tmp)
            }

            exercise8.setOnClickListener {
                val isFull = viewModel?.getExerciseState(7)
                var tmp = if (isFull == true) {
                    exercise8.setImageResource(R.drawable.ic_exercise_full_48dp)
                    false
                } else {
                    exercise8.setImageResource(R.drawable.ic_exercise_48dp)
                    true
                }
                viewModel?.setExerciseIsFull(7, tmp)
            }

            exercise9.setOnClickListener {
                val isFull = viewModel?.getExerciseState(8)
                var tmp = if (isFull == true) {
                    exercise9.setImageResource(R.drawable.ic_exercise_full_48dp)
                    false
                } else {
                    exercise9.setImageResource(R.drawable.ic_exercise_48dp)
                    true
                }
                viewModel?.setExerciseIsFull(8, tmp)
            }

            exercise10.setOnClickListener {
                val isFull = viewModel?.getExerciseState(9)
                var tmp = if (isFull == true) {
                    exercise10.setImageResource(R.drawable.ic_exercise_full_48dp)
                    false
                } else {
                    exercise10.setImageResource(R.drawable.ic_exercise_48dp)
                    true
                }
                viewModel?.setExerciseIsFull(9, tmp)
            }

            binding?.apply {
                exerciseTrackerSaveButton.setOnClickListener { goBackToTrackerScreen() }
            }
        }
    }

    fun goBackToTrackerScreen() {
        // TODO: save water intake to database
        sharedViewModel.saveExerciseIntake()
        findNavController().navigate(R.id.action_tracker_exercise_to_navigation_tracker)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}