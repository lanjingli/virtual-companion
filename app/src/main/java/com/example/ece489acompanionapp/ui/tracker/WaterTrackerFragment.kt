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
import com.example.ece489acompanionapp.databinding.FragmentTrackerWaterBinding
import android.content.pm.PackageManager
import android.net.Uri

class WaterTrackerFragment : Fragment() {
    private val sharedViewModel: TrackerViewModel by activityViewModels()
    private var _binding: FragmentTrackerWaterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var filledWaterCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val trackerViewModel =
            ViewModelProvider(this).get(TrackerViewModel::class.java)

        _binding = FragmentTrackerWaterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initWaterStates()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
        }

        binding?.apply {
            val waterLiters = countFilledCups()
            txtTodayWater.text = formatWaterLiters(waterLiters)
        }

        binding?.apply {
            water1.setOnClickListener {
                val isFull = viewModel?.getWaterState(0)
                var tmp = isFull
                tmp = if (isFull == true) {
                    water1.setImageResource(R.drawable.ic_water_full_48dp)
                    false
                } else {
                    water1.setImageResource(R.drawable.ic_water_empty_48dp)
                    true
                }
                viewModel?.setWaterIsFull(0, tmp)
            }

            water2.setOnClickListener {
                val isFull = viewModel?.getWaterState(1)
                var tmp = if (isFull == true) {
                    water2.setImageResource(R.drawable.ic_water_full_48dp)
                    false
                } else {
                    water2.setImageResource(R.drawable.ic_water_empty_48dp)
                    true
                }
                viewModel?.setWaterIsFull(1, tmp)
            }

            water3.setOnClickListener {
                val isFull = viewModel?.getWaterState(2)
                var tmp = if (isFull == true) {
                    water3.setImageResource(R.drawable.ic_water_full_48dp)
                    false
                } else {
                    water3.setImageResource(R.drawable.ic_water_empty_48dp)
                    true
                }
                viewModel?.setWaterIsFull(2, tmp)
            }

            water4.setOnClickListener {
                val isFull = viewModel?.getWaterState(3)
                var tmp = if (isFull == true) {
                    water4.setImageResource(R.drawable.ic_water_full_48dp)
                    false
                } else {
                    water4.setImageResource(R.drawable.ic_water_empty_48dp)
                    true
                }
                viewModel?.setWaterIsFull(3, tmp)
            }

            water5.setOnClickListener {
                val isFull = viewModel?.getWaterState(4)
                var tmp = if (isFull == true) {
                    water5.setImageResource(R.drawable.ic_water_full_48dp)
                    false
                } else {
                    water5.setImageResource(R.drawable.ic_water_empty_48dp)
                    true
                }
                viewModel?.setWaterIsFull(4, tmp)
            }

            water6.setOnClickListener {
                val isFull = viewModel?.getWaterState(5)
                var tmp = if (isFull == true) {
                    water6.setImageResource(R.drawable.ic_water_full_48dp)
                    false
                } else {
                    water6.setImageResource(R.drawable.ic_water_empty_48dp)
                    true
                }
                viewModel?.setWaterIsFull(5, tmp)
            }

            water7.setOnClickListener {
                val isFull = viewModel?.getWaterState(6)
                var tmp = if (isFull == true) {
                    water7.setImageResource(R.drawable.ic_water_full_48dp)
                    false
                } else {
                    water7.setImageResource(R.drawable.ic_water_empty_48dp)
                    true
                }
                viewModel?.setWaterIsFull(6, tmp)
            }

            water8.setOnClickListener {
                val isFull = viewModel?.getWaterState(7)
                var tmp = if (isFull == true) {
                    water8.setImageResource(R.drawable.ic_water_full_48dp)
                    false
                } else {
                    water8.setImageResource(R.drawable.ic_water_empty_48dp)
                    true
                }
                viewModel?.setWaterIsFull(7, tmp)
            }

            water9.setOnClickListener {
                val isFull = viewModel?.getWaterState(8)
                var tmp = if (isFull == true) {
                    water9.setImageResource(R.drawable.ic_water_full_48dp)
                    false
                } else {
                    water9.setImageResource(R.drawable.ic_water_empty_48dp)
                    true
                }
                viewModel?.setWaterIsFull(8, tmp)
            }

            water10.setOnClickListener {
                val isFull = viewModel?.getWaterState(9)
                var tmp = if (isFull == true) {
                    water10.setImageResource(R.drawable.ic_water_full_48dp)
                    false
                } else {
                    water10.setImageResource(R.drawable.ic_water_empty_48dp)
                    true
                }
                viewModel?.setWaterIsFull(9, tmp)
            }

            binding?.apply {
                waterTrackerShareButton.setOnClickListener {
                    val waterLiters = countFilledCups()
                    val tweetText = "I drank $waterLiters liters of water today! #WellnessCompanion #StayHydrated 🥤️"

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
                waterTrackerSaveButton.setOnClickListener {
                    val waterLiters = countFilledCups()
                    txtTodayWater.text = formatWaterLiters(waterLiters)
                    val recommendedLitersInt = txtRecommendedWater.text.split(" ")[0].toInt()
                    val isRecommendedMet = waterLiters >= recommendedLitersInt
                    if (isRecommendedMet) {
                        showPopupMessageWithShare("Great job! You have met or exceeded the recommended water intake goal.")
                    } else {
                        showPopupMessage("Keep it up! Drink more water to reach the recommended goal.")
                    }
                }
            }
        }
    }

    private fun initWaterStates() {
        if (isWaterFull(0) == true) binding.water1.setImageResource(R.drawable.ic_water_empty_48dp) else binding.water1.setImageResource(R.drawable.ic_water_full_48dp)
        if (isWaterFull(1) == true) binding.water2.setImageResource(R.drawable.ic_water_empty_48dp) else binding.water2.setImageResource(R.drawable.ic_water_full_48dp)
        if (isWaterFull(2) == true) binding.water3.setImageResource(R.drawable.ic_water_empty_48dp) else binding.water3.setImageResource(R.drawable.ic_water_full_48dp)
        if (isWaterFull(3) == true) binding.water4.setImageResource(R.drawable.ic_water_empty_48dp) else binding.water4.setImageResource(R.drawable.ic_water_full_48dp)
        if (isWaterFull(4) == true) binding.water5.setImageResource(R.drawable.ic_water_empty_48dp) else binding.water5.setImageResource(R.drawable.ic_water_full_48dp)
        if (isWaterFull(5) == true) binding.water6.setImageResource(R.drawable.ic_water_empty_48dp) else binding.water6.setImageResource(R.drawable.ic_water_full_48dp)
        if (isWaterFull(6) == true) binding.water7.setImageResource(R.drawable.ic_water_empty_48dp) else binding.water7.setImageResource(R.drawable.ic_water_full_48dp)
        if (isWaterFull(7) == true) binding.water8.setImageResource(R.drawable.ic_water_empty_48dp) else binding.water8.setImageResource(R.drawable.ic_water_full_48dp)
        if (isWaterFull(8) == true) binding.water9.setImageResource(R.drawable.ic_water_empty_48dp) else binding.water9.setImageResource(R.drawable.ic_water_full_48dp)
        if (isWaterFull(9) == true) binding.water10.setImageResource(R.drawable.ic_water_empty_48dp) else binding.water10.setImageResource(R.drawable.ic_water_full_48dp)
    }

    fun isWaterFull(ind: Int): Boolean? {
        return sharedViewModel.getWaterState(ind)
    }

    fun goBackToTrackerScreen() {
        // TODO: save water intake to database
        sharedViewModel.saveWaterIntake()
        findNavController().navigate(R.id.action_tracker_water_to_navigation_tracker)
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

    private fun countFilledCups(): Int {
        filledWaterCount = 0

        for (i in 0 until 10) {
            val isFull = sharedViewModel?.getWaterState(i)
            if (isFull == false) {
                filledWaterCount++
            }
        }
        return filledWaterCount
    }

    private fun formatWaterLiters(liters: Int): String {
        return "$liters L"
    }

    private fun showPopupMessageWithShare(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Goal Reached!")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialogBuilder.setNegativeButton("Share") { dialog, _ ->
            val tweetText = "I reached my water intake today! #WellnessCompanion #Hydrate 🥤"

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