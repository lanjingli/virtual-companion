package com.example.ece489acompanionapp.ui.tracker

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ece489acompanionapp.R
import com.example.ece489acompanionapp.databinding.FragmentTrackerSleepBinding
import com.example.ece489acompanionapp.ui.information.PersonalInfoViewModel
import com.example.ece489acompanionapp.ui.settings.SettingsViewModel

class SleepTrackerFragment : Fragment() {

    private val sharedViewModel: TrackerViewModel by activityViewModels()
    private var _binding: FragmentTrackerSleepBinding? = null
    private val infoViewModel: PersonalInfoViewModel by activityViewModels()
    private val settingsViewModel: SettingsViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var filledSleepCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val trackerViewModel =
            ViewModelProvider(this).get(TrackerViewModel::class.java)

        _binding = FragmentTrackerSleepBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initSleepStates()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
        }

        binding?.apply {
            val hoursSlept = countFilledBeds()
            txtTodaySleep.text = formatSleepHours(hoursSlept)
            txtRecommendedSleep.text = getGoalMessage()
        }

        binding?.apply {
            bed1.setOnClickListener {
                val isFull = viewModel?.getSleepState(0)
                var tmp = isFull
                tmp = if (isFull == true) {
                    bed1.setImageResource(R.drawable.ic_bed_full_48dp)
                    false
                } else {
                    bed1.setImageResource(R.drawable.ic_bed_empty_48dp)
                    true
                }
                viewModel?.setSleepIsFull(0, tmp)
            }

            bed2.setOnClickListener {
                val isFull = viewModel?.getSleepState(1)
                var tmp = if (isFull == true) {
                    bed2.setImageResource(R.drawable.ic_bed_full_48dp)
                    false
                } else {
                    bed2.setImageResource(R.drawable.ic_bed_empty_48dp)
                    true
                }
                viewModel?.setSleepIsFull(1, tmp)
            }

            bed3.setOnClickListener {
                val isFull = viewModel?.getSleepState(2)
                var tmp = if (isFull == true) {
                    bed3.setImageResource(R.drawable.ic_bed_full_48dp)
                    false
                } else {
                    bed3.setImageResource(R.drawable.ic_bed_empty_48dp)
                    true
                }
                viewModel?.setSleepIsFull(2, tmp)
            }

            bed4.setOnClickListener {
                val isFull = viewModel?.getSleepState(3)
                var tmp = if (isFull == true) {
                    bed4.setImageResource(R.drawable.ic_bed_full_48dp)
                    false
                } else {
                    bed4.setImageResource(R.drawable.ic_bed_empty_48dp)
                    true
                }
                viewModel?.setSleepIsFull(3, tmp)
            }

            bed5.setOnClickListener {
                val isFull = viewModel?.getSleepState(4)
                var tmp = if (isFull == true) {
                    bed5.setImageResource(R.drawable.ic_bed_full_48dp)
                    false
                } else {
                    bed5.setImageResource(R.drawable.ic_bed_empty_48dp)
                    true
                }
                viewModel?.setSleepIsFull(4, tmp)
            }

            bed6.setOnClickListener {
                val isFull = viewModel?.getSleepState(5)
                var tmp = if (isFull == true) {
                    bed6.setImageResource(R.drawable.ic_bed_full_48dp)
                    false
                } else {
                    bed6.setImageResource(R.drawable.ic_bed_empty_48dp)
                    true
                }
                viewModel?.setSleepIsFull(5, tmp)
            }

            bed7.setOnClickListener {
                val isFull = viewModel?.getSleepState(6)
                var tmp = if (isFull == true) {
                    bed7.setImageResource(R.drawable.ic_bed_full_48dp)
                    false
                } else {
                    bed7.setImageResource(R.drawable.ic_bed_empty_48dp)
                    true
                }
                viewModel?.setSleepIsFull(6, tmp)
            }

            bed8.setOnClickListener {
                val isFull = viewModel?.getSleepState(7)
                var tmp = if (isFull == true) {
                    bed8.setImageResource(R.drawable.ic_bed_full_48dp)
                    false
                } else {
                    bed8.setImageResource(R.drawable.ic_bed_empty_48dp)
                    true
                }
                viewModel?.setSleepIsFull(7, tmp)
            }

            bed9.setOnClickListener {
                val isFull = viewModel?.getSleepState(8)
                var tmp = if (isFull == true) {
                    bed9.setImageResource(R.drawable.ic_bed_full_48dp)
                    false
                } else {
                    bed9.setImageResource(R.drawable.ic_bed_empty_48dp)
                    true
                }
                viewModel?.setSleepIsFull(8, tmp)
            }

            bed10.setOnClickListener {
                val isFull = viewModel?.getSleepState(9)
                var tmp = if (isFull == true) {
                    bed10.setImageResource(R.drawable.ic_bed_full_48dp)
                    false
                } else {
                    bed10.setImageResource(R.drawable.ic_bed_empty_48dp)
                    true
                }
                viewModel?.setSleepIsFull(9, tmp)
            }
        }

        binding?.apply {
            sleepTrackerShareButton.setOnClickListener {
                val exerciseHours = countFilledBeds()
                val tweetText = "I slept $exerciseHours hours last night! #WellnessCompanion #Recovering 🛌"

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
            sleepTrackerSaveButton.setOnClickListener {
                val sleepHours = countFilledBeds()
                txtTodaySleep.text = formatSleepHours(sleepHours)
                val low = getGoalRange()
                if (sleepHours >= low) {
                    val addPointsMsg = addPointsAndGenMessage(10)
                    showPopupMessageWithShare("Great job! You have met or exceeded the recommended sleep goal.$addPointsMsg")
                } else {
                    showPopupMessage("It's Ok! Try to get more sleep tonight to reach your sleep goal tomorrow.")
                }
                goBackToTrackerScreen()
            }
        }
    }

    private fun initSleepStates() {
        if (isSleepFull(0) == true) binding.bed1.setImageResource(R.drawable.ic_bed_empty_48dp) else binding.bed1.setImageResource(R.drawable.ic_bed_full_48dp)
        if (isSleepFull(1) == true) binding.bed2.setImageResource(R.drawable.ic_bed_empty_48dp) else binding.bed2.setImageResource(R.drawable.ic_bed_full_48dp)
        if (isSleepFull(2) == true) binding.bed3.setImageResource(R.drawable.ic_bed_empty_48dp) else binding.bed3.setImageResource(R.drawable.ic_bed_full_48dp)
        if (isSleepFull(3) == true) binding.bed4.setImageResource(R.drawable.ic_bed_empty_48dp) else binding.bed4.setImageResource(R.drawable.ic_bed_full_48dp)
        if (isSleepFull(4) == true) binding.bed5.setImageResource(R.drawable.ic_bed_empty_48dp) else binding.bed5.setImageResource(R.drawable.ic_bed_full_48dp)
        if (isSleepFull(5) == true) binding.bed6.setImageResource(R.drawable.ic_bed_empty_48dp) else binding.bed6.setImageResource(R.drawable.ic_bed_full_48dp)
        if (isSleepFull(6) == true) binding.bed7.setImageResource(R.drawable.ic_bed_empty_48dp) else binding.bed7.setImageResource(R.drawable.ic_bed_full_48dp)
        if (isSleepFull(7) == true) binding.bed8.setImageResource(R.drawable.ic_bed_empty_48dp) else binding.bed8.setImageResource(R.drawable.ic_bed_full_48dp)
        if (isSleepFull(8) == true) binding.bed9.setImageResource(R.drawable.ic_bed_empty_48dp) else binding.bed9.setImageResource(R.drawable.ic_bed_full_48dp)
        if (isSleepFull(9) == true) binding.bed10.setImageResource(R.drawable.ic_bed_empty_48dp) else binding.bed10.setImageResource(R.drawable.ic_bed_full_48dp)
    }

    private fun getRecommendedSleep(age: Int?): Int {
        var recommended = 8
        if (age != null) {
            if (age <= 1) {
                recommended = 14
            }
            else if (age in 1..2) {
                recommended = 13
            }
            else if (age in 3..5){
                recommended = 12
            }
            else if (age in 6..12){
                recommended = 10
            }
            else if (age in 13..18){
                recommended = 9
            }
            else if (age >= 18){
                recommended = 8
            }
        }
        return recommended
    }

    fun isSleepFull(ind: Int): Boolean? {
        return sharedViewModel.getSleepState(ind)
    }

    fun goBackToTrackerScreen() {
        // TODO: save hours slept to database
        sharedViewModel.saveHoursSlept()
        findNavController().navigate(R.id.action_tracker_sleep_to_navigation_tracker)
    }

    private fun showPopupMessage(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Close!")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun countFilledBeds(): Int {
        filledSleepCount = 0

        for (i in 0 until 10) {
            val isFull = sharedViewModel?.getSleepState(i)
            if (isFull == false) {
                filledSleepCount++
            }
        }
        return filledSleepCount
    }

    private fun formatSleepHours(hours: Int): String {
        return "$hours H"
    }

    private fun showPopupMessageWithShare(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Goal Reached!")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialogBuilder.setNegativeButton("Share") { dialog, _ ->
            val tweetText = "I reached my sleep goal last night! #WellnessCompanion #WellRested ☀️"

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
        var curGoal: String = binding.txtRecommendedSleep.text.toString()

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
        val isGoalSet = settingsViewModel.getCustomSleepSelected()
        var message = if (isGoalSet == "Default") {
            //not been set, default
            val age = infoViewModel.getAge()
            formatSleepHours(getRecommendedSleep(age))
        } else {
            isGoalSet!!
        }
        return message
    }

    private fun addPointsAndGenMessage(points: Int): String? {
        val isRecomMet = sharedViewModel.getRecomMet("sleep")

        var message = if (isRecomMet == false) {
            sharedViewModel.setTotalPoints(points)
            sharedViewModel.setRecomMet("sleep", true)
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