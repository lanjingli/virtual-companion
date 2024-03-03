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
import com.example.ece489acompanionapp.databinding.FragmentTrackerFoodBinding
import com.example.ece489acompanionapp.ui.calendar.FoodTrackerViewModel

class FoodTrackerFragment : Fragment() {
    private val sharedViewModel: TrackerViewModel by activityViewModels()
    private var _binding: FragmentTrackerFoodBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var filledFoodCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val trackerViewModel =
            ViewModelProvider(this).get(TrackerViewModel::class.java)

        _binding = FragmentTrackerFoodBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initFoodStates()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = sharedViewModel
        }

        binding?.apply {
            val foodCalories = countFilledBurgers()
            txtTodayFood.text = formatFoodCalories(foodCalories)
        }

        binding?.apply {
            food1.setOnClickListener {
                val isFull = viewModel?.getFoodState(0)
                var tmp = if (isFull == true) {
                    food1.setImageResource(R.drawable.ic_burger_full_48dp)
                    false
                } else {
                    food1.setImageResource(R.drawable.ic_burger_empty_48dp)
                    true
                }
                viewModel?.setFoodIsFull(0, tmp)
            }

            food2.setOnClickListener {
                val isFull = viewModel?.getFoodState(1)
                var tmp = if (isFull == true) {
                    food2.setImageResource(R.drawable.ic_burger_full_48dp)
                    false
                } else {
                    food2.setImageResource(R.drawable.ic_burger_empty_48dp)
                    true
                }
                viewModel?.setFoodIsFull(1, tmp)
            }

            food3.setOnClickListener {
                val isFull = viewModel?.getFoodState(2)
                var tmp = if (isFull == true) {
                    food3.setImageResource(R.drawable.ic_burger_full_48dp)
                    false
                } else {
                    food3.setImageResource(R.drawable.ic_burger_empty_48dp)
                    true
                }
                viewModel?.setFoodIsFull(2, tmp)
            }

            food4.setOnClickListener {
                val isFull = viewModel?.getFoodState(3)
                var tmp = if (isFull == true) {
                    food4.setImageResource(R.drawable.ic_burger_full_48dp)
                    false
                } else {
                    food4.setImageResource(R.drawable.ic_burger_empty_48dp)
                    true
                }
                viewModel?.setFoodIsFull(3, tmp)
            }

            food5.setOnClickListener {
                val isFull = viewModel?.getFoodState(4)
                var tmp = if (isFull == true) {
                    food5.setImageResource(R.drawable.ic_burger_full_48dp)
                    false
                } else {
                    food5.setImageResource(R.drawable.ic_burger_empty_48dp)
                    true
                }
                viewModel?.setFoodIsFull(4, tmp)
            }

            food6.setOnClickListener {
                val isFull = viewModel?.getFoodState(5)
                var tmp = if (isFull == true) {
                    food6.setImageResource(R.drawable.ic_burger_full_48dp)
                    false
                } else {
                    food6.setImageResource(R.drawable.ic_burger_empty_48dp)
                    true
                }
                viewModel?.setFoodIsFull(5, tmp)
            }

            food7.setOnClickListener {
                val isFull = viewModel?.getFoodState(6)
                var tmp = if (isFull == true) {
                    food7.setImageResource(R.drawable.ic_burger_full_48dp)
                    false
                } else {
                    food7.setImageResource(R.drawable.ic_burger_empty_48dp)
                    true
                }
                viewModel?.setFoodIsFull(6, tmp)
            }

            food8.setOnClickListener {
                val isFull = viewModel?.getFoodState(7)
                var tmp = if (isFull == true) {
                    food8.setImageResource(R.drawable.ic_burger_full_48dp)
                    false
                } else {
                    food8.setImageResource(R.drawable.ic_burger_empty_48dp)
                    true
                }
                viewModel?.setFoodIsFull(7, tmp)
            }

            food9.setOnClickListener {
                val isFull = viewModel?.getFoodState(8)
                var tmp = if (isFull == true) {
                    food9.setImageResource(R.drawable.ic_burger_full_48dp)
                    false
                } else {
                    food9.setImageResource(R.drawable.ic_burger_empty_48dp)
                    true
                }
                viewModel?.setFoodIsFull(8, tmp)
            }

            food10.setOnClickListener {
                val isFull = viewModel?.getFoodState(9)
                var tmp = if (isFull == true) {
                    food10.setImageResource(R.drawable.ic_burger_full_48dp)
                    false
                } else {
                    food10.setImageResource(R.drawable.ic_burger_empty_48dp)
                    true
                }
                viewModel?.setFoodIsFull(9, tmp)
            }

            binding?.apply {
                foodTrackerShareButton.setOnClickListener {
                    val foodCalories = countFilledBurgers()
                    val tweetText = "I've eaten $foodCalories calories today! #WellnessCompanion #EatingWell 🍗"

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
                foodTrackerSaveButton.setOnClickListener {
                    val foodCalories = countFilledBurgers()
                    txtTodayFood.text = formatFoodCalories(foodCalories)
                    val recommendedCaloriesInt = txtRecommendedFood.text.split(" ")[0].toInt()
                    val isRecommendedMet = foodCalories >= recommendedCaloriesInt
                    if (isRecommendedMet) {
                        showPopupMessageWithShare("Great job! You have met or exceeded the recommended food intake goal.")
                    } else {
                        showPopupMessage("Keep it up! Eat some more to reach the recommended goal.")
                    }
                }
            }
        }
    }

    private fun initFoodStates() {
        if (isFoodFull(0) == true) binding.food1.setImageResource(R.drawable.ic_burger_empty_48dp) else binding.food1.setImageResource(
            R.drawable.ic_burger_full_48dp)
        if (isFoodFull(1) == true) binding.food2.setImageResource(R.drawable.ic_burger_empty_48dp) else binding.food2.setImageResource(
            R.drawable.ic_burger_full_48dp)
        if (isFoodFull(2) == true) binding.food3.setImageResource(R.drawable.ic_burger_empty_48dp) else binding.food3.setImageResource(
            R.drawable.ic_burger_full_48dp)
        if (isFoodFull(3) == true) binding.food4.setImageResource(R.drawable.ic_burger_empty_48dp) else binding.food4.setImageResource(
            R.drawable.ic_burger_full_48dp)
        if (isFoodFull(4) == true) binding.food5.setImageResource(R.drawable.ic_burger_empty_48dp) else binding.food5.setImageResource(
            R.drawable.ic_burger_full_48dp)
        if (isFoodFull(5) == true) binding.food6.setImageResource(R.drawable.ic_burger_empty_48dp) else binding.food6.setImageResource(
            R.drawable.ic_burger_full_48dp)
        if (isFoodFull(6) == true) binding.food7.setImageResource(R.drawable.ic_burger_empty_48dp) else binding.food7.setImageResource(
            R.drawable.ic_burger_full_48dp)
        if (isFoodFull(7) == true) binding.food8.setImageResource(R.drawable.ic_burger_empty_48dp) else binding.food8.setImageResource(
            R.drawable.ic_burger_full_48dp)
        if (isFoodFull(8) == true) binding.food9.setImageResource(R.drawable.ic_burger_empty_48dp) else binding.food9.setImageResource(
            R.drawable.ic_burger_full_48dp)
        if (isFoodFull(9) == true) binding.food10.setImageResource(R.drawable.ic_burger_empty_48dp) else binding.food10.setImageResource(
            R.drawable.ic_burger_full_48dp)
    }

    fun isFoodFull(ind: Int): Boolean? {
        return sharedViewModel.getFoodState(ind)
    }


    fun goBackToTrackerScreen() {
        // TODO: save water intake to database
        sharedViewModel.saveFoodIntake()
        findNavController().navigate(R.id.action_tracker_food_to_navigation_tracker)
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

    private fun countFilledBurgers(): Int {
        filledFoodCount = 0

        for (i in 0 until 10) {
            val isFull = sharedViewModel?.getFoodState(i)
            if (isFull == false) {
                filledFoodCount++
            }
        }
        return filledFoodCount * 500
    }

    private fun formatFoodCalories(calories: Int): String {
        return "$calories cal"
    }

    private fun showPopupMessageWithShare(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Goal Reached!")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialogBuilder.setNegativeButton("Share") { dialog, _ ->
            val tweetText = "I reached my food intake goal today! #WellnessCompanion #food 😋"

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