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
import com.example.ece489acompanionapp.ui.information.PersonalInfoViewModel
import com.example.ece489acompanionapp.ui.settings.SettingsViewModel

class ExerciseTrackerFragment : Fragment() {
    private val sharedViewModel: TrackerViewModel by activityViewModels()
    private var _binding: FragmentTrackerExerciseBinding? = null
    private val infoViewModel: PersonalInfoViewModel by activityViewModels()
    private val settingsViewModel: SettingsViewModel by activityViewModels()

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
            val exerciseMinutes = countFilledDumbells()
            txtTodayExercise.text = formatExerciseMinutes(exerciseMinutes)
            txtRecommendedExercise.text = getGoalMessage()
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
                exerciseTrackerNotificationButton.setOnClickListener {
                    val action = ExerciseTrackerFragmentDirections.actionTrackerExerciseToNotifications(trackerPage = "exercise")
                    findNavController().navigate(action)
                }
            }

            binding?.apply {
                exerciseTrackerShareButton.setOnClickListener {
                    val exerciseMinutes = countFilledDumbells()
                    val tweetText = "I completed $exerciseMinutes minutes of exercise today! #WellnessCompanion #StayingFit 🏋️‍♂️"

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
                    val exerciseMinutes = countFilledDumbells()
                    txtTodayExercise.text = formatExerciseMinutes(exerciseMinutes)
                    val low = getGoalRange()
                    if (exerciseMinutes >= low) {
                        val addPointsMsg = addPointsAndGenMessage(10)
                        showPopupMessageWithShare("Great job! You have met or exceeded the recommended exercise goal.$addPointsMsg")
                    } else {
                        showPopupMessage("Keep it up! Do some more exercise to reach the recommended goal.")
                    }
                    goBackToTrackerScreen()
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

    private fun getRecommendedExercise(age: Int?): Int {
        var recommended = 60
        if (age != null) {
            if (age <= 5) {
                recommended = 90
            }
            else if (age in 6..17) {
                recommended = 60
            }
            else if (age in 18..64){
                recommended = 22
            }
            else if (age >= 65){
                recommended = 22
            }
        }
        return recommended
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
        alertDialogBuilder.setTitle("Almost There!")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun countFilledDumbells(): Int {
        filledExerciseCount = 0

        for (i in 0 until 10) {
            val isFull = sharedViewModel?.getExerciseState(i)
            if (isFull == false) {
                filledExerciseCount++
            }
        }
        return filledExerciseCount * 15
    }

    private fun formatExerciseMinutes(minutes: Int): String {
        return "$minutes mins"
    }

    private fun showPopupMessageWithShare(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Goal Reached!")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialogBuilder.setNegativeButton("Share") { dialog, _ ->
            val tweetText = "I reached my exercise goal today! #WellnessCompanion #FitnessGoals 🏋️‍♂️"

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

    private fun getGoalRange(): Int {
        var curGoal: String = binding.txtRecommendedExercise.text.toString()

        var low = if (curGoal.contains("<")) {
            1
        } else if (curGoal.contains("+")) {
            curGoal.split(" ")[0].toInt()
        } else if (curGoal.contains("-")) {
            curGoal.split(" ")[0].split("-")[0].toInt()
        } else {
            curGoal.split(" ")[0].toInt()
        }
        return low
    }

    private fun getGoalMessage(): String {
        val isGoalSet = settingsViewModel.getCustomExerciseSelected()
        var message = if (isGoalSet == "Default") {
            //not been set, default
            val age = infoViewModel.getAge()
            formatExerciseMinutes(getRecommendedExercise(age))
        } else {
            isGoalSet!!
        }
        return message
    }

    private fun addPointsAndGenMessage(points: Int): String? {
        val isRecomMet = sharedViewModel.getRecomMet("exercise")

        var message = if (isRecomMet == false) {
            sharedViewModel.setTotalPoints(points)
            sharedViewModel.setRecomMet("exercise", true)
            val totalPoints = sharedViewModel.getTotalPoints()
            "\n+10 Points.\nTotal points: $totalPoints"
        } else {
            val totalPoints = sharedViewModel.getTotalPoints()
            "\n+0 Points, today's points have already been awarded.\nTotal points: $totalPoints"
        }
        return message
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}