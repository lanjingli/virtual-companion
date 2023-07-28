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
import com.example.ece489acompanionapp.databinding.FragmentTrackerWaterBinding

class WaterTrackerFragment : Fragment() {
    private val sharedViewModel: TrackerViewModel by activityViewModels()
    private var _binding: FragmentTrackerWaterBinding? = null

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
                waterTrackerSaveButton.setOnClickListener { goBackToTrackerScreen() }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}