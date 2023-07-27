package com.example.ece489acompanionapp.ui.companion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.ece489acompanionapp.R
import com.example.ece489acompanionapp.databinding.FragmentCompanionBinding
import com.example.ece489acompanionapp.ui.tracker.TrackerViewModel

class CompanionFragment : Fragment(0) {

    private var _binding: FragmentCompanionBinding? = null
    private val sharedViewModel: CompanionViewModel by activityViewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val companionViewModel =
            ViewModelProvider(this).get(CompanionViewModel::class.java)

        _binding = FragmentCompanionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}