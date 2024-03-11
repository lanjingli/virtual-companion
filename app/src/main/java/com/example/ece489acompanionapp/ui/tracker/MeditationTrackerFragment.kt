package com.example.ece489acompanionapp.ui.tracker

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ece489acompanionapp.R
import com.example.ece489acompanionapp.databinding.FragmentTrackerExerciseBinding
import com.example.ece489acompanionapp.databinding.FragmentTrackerMeditationBinding
import com.example.ece489acompanionapp.ui.calendar.MeditationTrackerViewModel
import com.example.ece489acompanionapp.ui.settings.SettingsViewModel

class MeditationTrackerFragment : Fragment() {
    private val sharedViewModel: TrackerViewModel by activityViewModels()
    private var _binding: FragmentTrackerMeditationBinding? = null
    private val settingsViewModel: SettingsViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var filledMeditationCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val trackerViewModel =
            ViewModelProvider(this).get(TrackerViewModel::class.java)

        _binding = FragmentTrackerMeditationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initMeditationStates()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = sharedViewModel
        }

        binding?.apply {
            val meditationMins = countFilledMeditation()
            txtTodayMeditation.text = formatMeditationMins(meditationMins)
            val customMeditationSelection = getGoalMessage()
            if (customMeditationSelection != "") {
                txtRecommendedMeditation.text = customMeditationSelection
            }
        }

        binding?.apply {
            meditation1.setOnClickListener {
                val isFull = viewModel?.getMeditationState(0)
                var tmp = if (isFull == true) {
                    meditation1.setImageResource(R.drawable.ic_meditation_full_48dp)
                    false
                } else {
                    meditation1.setImageResource(R.drawable.ic_meditation_empty_48dp)
                    true
                }
                viewModel?.setMeditationIsFull(0, tmp)
            }

            meditation2.setOnClickListener {
                val isFull = viewModel?.getMeditationState(1)
                var tmp = if (isFull == true) {
                    meditation2.setImageResource(R.drawable.ic_meditation_full_48dp)
                    false
                } else {
                    meditation2.setImageResource(R.drawable.ic_meditation_empty_48dp)
                    true
                }
                viewModel?.setMeditationIsFull(1, tmp)
            }

            meditation3.setOnClickListener {
                val isFull = viewModel?.getMeditationState(2)
                var tmp = if (isFull == true) {
                    meditation3.setImageResource(R.drawable.ic_meditation_full_48dp)
                    false
                } else {
                    meditation3.setImageResource(R.drawable.ic_meditation_empty_48dp)
                    true
                }
                viewModel?.setMeditationIsFull(2, tmp)
            }

            meditation4.setOnClickListener {
                val isFull = viewModel?.getMeditationState(3)
                var tmp = if (isFull == true) {
                    meditation4.setImageResource(R.drawable.ic_meditation_full_48dp)
                    false
                } else {
                    meditation4.setImageResource(R.drawable.ic_meditation_empty_48dp)
                    true
                }
                viewModel?.setMeditationIsFull(3, tmp)
            }

            meditation5.setOnClickListener {
                val isFull = viewModel?.getMeditationState(4)
                var tmp = if (isFull == true) {
                    meditation5.setImageResource(R.drawable.ic_meditation_full_48dp)
                    false
                } else {
                    meditation5.setImageResource(R.drawable.ic_meditation_empty_48dp)
                    true
                }
                viewModel?.setMeditationIsFull(4, tmp)
            }

            meditation6.setOnClickListener {
                val isFull = viewModel?.getMeditationState(5)
                var tmp = if (isFull == true) {
                    meditation6.setImageResource(R.drawable.ic_meditation_full_48dp)
                    false
                } else {
                    meditation6.setImageResource(R.drawable.ic_meditation_empty_48dp)
                    true
                }
                viewModel?.setMeditationIsFull(5, tmp)
            }

            meditation7.setOnClickListener {
                val isFull = viewModel?.getMeditationState(6)
                var tmp = if (isFull == true) {
                    meditation7.setImageResource(R.drawable.ic_meditation_full_48dp)
                    false
                } else {
                    meditation7.setImageResource(R.drawable.ic_meditation_empty_48dp)
                    true
                }
                viewModel?.setMeditationIsFull(6, tmp)
            }

            meditation8.setOnClickListener {
                val isFull = viewModel?.getMeditationState(7)
                var tmp = if (isFull == true) {
                    meditation8.setImageResource(R.drawable.ic_meditation_full_48dp)
                    false
                } else {
                    meditation8.setImageResource(R.drawable.ic_meditation_empty_48dp)
                    true
                }
                viewModel?.setMeditationIsFull(7, tmp)
            }

            meditation9.setOnClickListener {
                val isFull = viewModel?.getMeditationState(8)
                var tmp = if (isFull == true) {
                    meditation9.setImageResource(R.drawable.ic_meditation_full_48dp)
                    false
                } else {
                    meditation9.setImageResource(R.drawable.ic_meditation_empty_48dp)
                    true
                }
                viewModel?.setMeditationIsFull(8, tmp)
            }

            meditation10.setOnClickListener {
                val isFull = viewModel?.getMeditationState(9)
                var tmp = if (isFull == true) {
                    meditation10.setImageResource(R.drawable.ic_meditation_full_48dp)
                    false
                } else {
                    meditation10.setImageResource(R.drawable.ic_meditation_empty_48dp)
                    true
                }
                viewModel?.setMeditationIsFull(9, tmp)
            }

            binding?.apply {
                meditationTrackerNotificationButton.setOnClickListener {
                    val action = MeditationTrackerFragmentDirections.actionTrackerMeditationToNotifications(trackerPage = "meditation")
                    findNavController().navigate(action)
                }
            }

            binding?.apply {
                meditationTrackerShareButton.setOnClickListener {
                    val meditationMins = countFilledMeditation()
                    val tweetText = "I've meditated for $meditationMins minutes today! #WellnessCompanion #selfcare ☯️️"

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
                meditationTrackerSaveButton.setOnClickListener {
                    val meditationMins = countFilledMeditation()
                    txtTodayMeditation.text = formatMeditationMins(meditationMins)
                    val low = getGoalRange()
                    if (meditationMins >= low) {
                        val addPointsMsg = addPointsAndGenMessage(10)
                        showPopupMessageWithShare("Great job! You have met or exceeded the recommended meditation goal.$addPointsMsg")
                    } else {
                        showPopupMessage("Keep it up! Meditate some more to reach the recommended goal.")
                    }
                    goBackToTrackerScreen()
                }
            }
        }
    }

    private fun initMeditationStates() {
        if (isMeditationFull(0) == true) binding.meditation1.setImageResource(R.drawable.ic_meditation_empty_48dp) else binding.meditation1.setImageResource(R.drawable.ic_meditation_full_48dp)
        if (isMeditationFull(1) == true) binding.meditation2.setImageResource(R.drawable.ic_meditation_empty_48dp) else binding.meditation2.setImageResource(R.drawable.ic_meditation_full_48dp)
        if (isMeditationFull(2) == true) binding.meditation3.setImageResource(R.drawable.ic_meditation_empty_48dp) else binding.meditation3.setImageResource(R.drawable.ic_meditation_full_48dp)
        if (isMeditationFull(3) == true) binding.meditation4.setImageResource(R.drawable.ic_meditation_empty_48dp) else binding.meditation4.setImageResource(R.drawable.ic_meditation_full_48dp)
        if (isMeditationFull(4) == true) binding.meditation5.setImageResource(R.drawable.ic_meditation_empty_48dp) else binding.meditation5.setImageResource(R.drawable.ic_meditation_full_48dp)
        if (isMeditationFull(5) == true) binding.meditation6.setImageResource(R.drawable.ic_meditation_empty_48dp) else binding.meditation6.setImageResource(R.drawable.ic_meditation_full_48dp)
        if (isMeditationFull(6) == true) binding.meditation7.setImageResource(R.drawable.ic_meditation_empty_48dp) else binding.meditation7.setImageResource(R.drawable.ic_meditation_full_48dp)
        if (isMeditationFull(7) == true) binding.meditation8.setImageResource(R.drawable.ic_meditation_empty_48dp) else binding.meditation8.setImageResource(R.drawable.ic_meditation_full_48dp)
        if (isMeditationFull(8) == true) binding.meditation9.setImageResource(R.drawable.ic_meditation_empty_48dp) else binding.meditation9.setImageResource(R.drawable.ic_meditation_full_48dp)
        if (isMeditationFull(9) == true) binding.meditation10.setImageResource(R.drawable.ic_meditation_empty_48dp) else binding.meditation10.setImageResource(R.drawable.ic_meditation_full_48dp)
    }

    fun isMeditationFull(ind: Int): Boolean? {
        return sharedViewModel.getMeditationState(ind)
    }


    fun goBackToTrackerScreen() {
        // TODO: save water intake to database
        sharedViewModel.saveMeditationIntake()
        findNavController().navigate(R.id.action_tracker_meditation_to_navigation_tracker)
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

    private fun countFilledMeditation(): Int {
        filledMeditationCount = 0

        for (i in 0 until 10) {
            val isFull = sharedViewModel?.getMeditationState(i)
            if (isFull == false) {
                filledMeditationCount++
            }
        }
        return filledMeditationCount * 5
    }

    private fun formatMeditationMins(mins: Int): String {
        return "$mins mins"
    }

    private fun showPopupMessageWithShare(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Goal Reached!")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialogBuilder.setNegativeButton("Share") { dialog, _ ->
            val tweetText = "I reached my meditation goal today! #WellnessCompanion #zen 🧘‍♂️"

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
        var curGoal: String = binding.txtRecommendedMeditation.text.toString()

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
        val isGoalSet = settingsViewModel.getCustomMeditationSelected()

        var message = ""
        if (isGoalSet != "Default") {
            message = isGoalSet!!
        }

        return message
    }

    private fun addPointsAndGenMessage(points: Int): String? {
        val isRecomMet = sharedViewModel.getRecomMet("meditation")

        var message = if (isRecomMet == false) {
            sharedViewModel.setTotalPoints(points)
            sharedViewModel.setRecomMet("meditation", true)
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