package com.example.ece489acompanionapp.ui.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ece489acompanionapp.databinding.FragmentTrackerSleepBinding

class SleepTrackerFragment : Fragment() {

    private var _binding: FragmentTrackerSleepBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sleepTrackerViewModel =
            ViewModelProvider(this).get(SleepTrackerViewModel::class.java)

        _binding = FragmentTrackerSleepBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.trackerSleep
        sleepTrackerViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}