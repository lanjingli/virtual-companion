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
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

class ExerciseTrackerFragment : Fragment() {
    private val sharedViewModel: TrackerViewModel by activityViewModels()
    private var _binding: FragmentTrackerExerciseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var filledExerciseCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val trackerViewModel =
            ViewModelProvider(this).get(TrackerViewModel::class.java)

        _binding = FragmentTrackerExerciseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initExerciseStates()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = sharedViewModel
        }

        binding?.apply {
            val exerciseHours = countFilledExerciseBeds()
            txtTodayExercise.text = exerciseHours.toString()
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
                exerciseTrackerShareButton.setOnClickListener {
                    val exerciseHours = countFilledExerciseBeds()
                    val tweetText = "I completed $exerciseHours of exercise today! #FitnessGoals 🏋️‍♂️"

                    val tweetIntent = Intent(Intent.ACTION_SEND)
                    tweetIntent.putExtra(Intent.EXTRA_TEXT, tweetText)
                    tweetIntent.type = "text/plain"

                    val tweetAppPackage = "com.twitter.android"
                    val tweetAppIntent = activity?.packageManager?.getLaunchIntentForPackage(tweetAppPackage)

                    if (tweetAppIntent != null) {
                        startActivity(tweetIntent.setPackage(tweetAppPackage))
                    } else {
                        startActivity(Intent.createChooser(tweetIntent, "Share on Twitter"))
                    }
                }
            }

            binding?.apply {
                exerciseTrackerSaveButton.setOnClickListener {
                    val exerciseHours = countFilledExerciseBeds()
                    txtTodayExercise.text = formatExerciseHours(exerciseHours)
                    val recommendedHoursInt = txtRecommendedExercise.text.split(" ")[0].toInt()
                    val isRecommendedMet = exerciseHours >= recommendedHoursInt
                    if (isRecommendedMet) {
                        showPopupMessageWithShare("Great job! You have met or exceeded the recommended exercise goal.")
                    } else {
                        showPopupMessage("Keep it up! You still have some exercise left to reach the recommended goal.")
                    }
                }
            }
        }
    }

    private fun initExerciseStates() {
        if (isExerciseFull(0) == true) binding.exercise1.setImageResource(R.drawable.ic_exercise_48dp) else binding.exercise1.setImageResource(R.drawable.ic_exercise_full_48dp)
        if (isExerciseFull(1) == true) binding.exercise2.setImageResource(R.drawable.ic_exercise_48dp) else binding.exercise2.setImageResource(R.drawable.ic_exercise_full_48dp)
        if (isExerciseFull(2) == true) binding.exercise3.setImageResource(R.drawable.ic_exercise_48dp) else binding.exercise3.setImageResource(R.drawable.ic_exercise_full_48dp)
        if (isExerciseFull(3) == true) binding.exercise4.setImageResource(R.drawable.ic_exercise_48dp) else binding.exercise4.setImageResource(R.drawable.ic_exercise_full_48dp)
        if (isExerciseFull(4) == true) binding.exercise5.setImageResource(R.drawable.ic_exercise_48dp) else binding.exercise5.setImageResource(R.drawable.ic_exercise_full_48dp)
        if (isExerciseFull(5) == true) binding.exercise6.setImageResource(R.drawable.ic_exercise_48dp) else binding.exercise6.setImageResource(R.drawable.ic_exercise_full_48dp)
        if (isExerciseFull(6) == true) binding.exercise7.setImageResource(R.drawable.ic_exercise_48dp) else binding.exercise7.setImageResource(R.drawable.ic_exercise_full_48dp)
        if (isExerciseFull(7) == true) binding.exercise8.setImageResource(R.drawable.ic_exercise_48dp) else binding.exercise8.setImageResource(R.drawable.ic_exercise_full_48dp)
        if (isExerciseFull(8) == true) binding.exercise9.setImageResource(R.drawable.ic_exercise_48dp) else binding.exercise9.setImageResource(R.drawable.ic_exercise_full_48dp)
        if (isExerciseFull(9) == true) binding.exercise10.setImageResource(R.drawable.ic_exercise_48dp) else binding.exercise10.setImageResource(R.drawable.ic_exercise_full_48dp)
    }

    fun isExerciseFull(ind: Int): Boolean? {
        return sharedViewModel.getExerciseState(ind)
    }


    fun goBackToTrackerScreen() {
        // TODO: save water intake to database
        sharedViewModel.saveExerciseIntake()
        findNavController().navigate(R.id.action_tracker_exercise_to_navigation_tracker)
    }

    private fun showPopupMessage(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Popup Message")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun countFilledExerciseBeds(): Int {
        filledExerciseCount = 0

        for (i in 0 until 10) {
            val isFull = sharedViewModel?.getExerciseState(i)
            if (isFull == false) {
                filledExerciseCount++
            }
        }
        return filledExerciseCount
    }

    private fun formatExerciseHours(hours: Int): String {
        return "$hours H"
    }

    private fun showPopupMessageWithShare(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Popup Message")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialogBuilder.setNegativeButton("Share") { dialog, _ ->
            val tweetText = "I reached my exercise goal today! #FitnessGoals 🏋️‍♂️"

            val tweetIntent = Intent(Intent.ACTION_SEND)
            tweetIntent.putExtra(Intent.EXTRA_TEXT, tweetText)
            tweetIntent.type = "text/plain"

            val tweetAppPackage = "com.twitter.android"
            val tweetAppIntent = activity?.packageManager?.getLaunchIntentForPackage(tweetAppPackage)

            if (tweetAppIntent != null) {
                startActivity(tweetIntent.setPackage(tweetAppPackage))
            } else {
                startActivity(Intent.createChooser(tweetIntent, "Share on Twitter"))
            }

            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}