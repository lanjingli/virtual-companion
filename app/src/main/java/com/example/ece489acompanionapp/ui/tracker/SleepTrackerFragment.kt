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
import com.example.ece489acompanionapp.databinding.FragmentTrackerSleepBinding

class SleepTrackerFragment : Fragment() {

    private val sharedViewModel: TrackerViewModel by activityViewModels()
    private var _binding: FragmentTrackerSleepBinding? = null

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

        _binding = FragmentTrackerSleepBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
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
                viewModel?.setWaterIsFull(0, tmp)
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
                viewModel?.setWaterIsFull(1, tmp)
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
                viewModel?.setWaterIsFull(2, tmp)
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
                viewModel?.setWaterIsFull(3, tmp)
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
                viewModel?.setWaterIsFull(4, tmp)
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
                viewModel?.setWaterIsFull(5, tmp)
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
                viewModel?.setWaterIsFull(6, tmp)
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
                viewModel?.setWaterIsFull(7, tmp)
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
                viewModel?.setWaterIsFull(8, tmp)
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
                viewModel?.setWaterIsFull(9, tmp)
            }

            bed11.setOnClickListener {
                val isFull = viewModel?.getSleepState(10)
                var tmp = if (isFull == true) {
                    bed11.setImageResource(R.drawable.ic_bed_full_48dp)
                    false
                } else {
                    bed11.setImageResource(R.drawable.ic_bed_empty_48dp)
                    true
                }
                viewModel?.setWaterIsFull(10, tmp)
            }

            bed12.setOnClickListener {
                val isFull = viewModel?.getSleepState(11)
                var tmp = if (isFull == true) {
                    bed12.setImageResource(R.drawable.ic_bed_full_48dp)
                    false
                } else {
                    bed12.setImageResource(R.drawable.ic_bed_empty_48dp)
                    true
                }
                viewModel?.setWaterIsFull(11, tmp)
            }

            binding?.apply {
                sleepTrackerSaveButton.setOnClickListener { goBackToTrackerScreen() }
            }
        }
    }

    fun goBackToTrackerScreen() {
        // TODO: save hours slept to database
        sharedViewModel.saveSleepIntake()
        findNavController().navigate(R.id.action_tracker_sleep_to_navigation_tracker)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}