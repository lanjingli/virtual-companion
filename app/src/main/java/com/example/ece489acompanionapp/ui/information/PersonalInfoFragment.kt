package com.example.ece489acompanionapp.ui.information

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.ece489acompanionapp.R
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.ece489acompanionapp.databinding.FragmentPersonalInfoBinding
import com.google.android.material.textfield.TextInputLayout

class PersonalInfoFragment : Fragment() {
    private var _binding: FragmentPersonalInfoBinding? = null

    private lateinit var viewModel: PersonalInfoViewModel
    private var selectedGender: String = ""

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_personal_info, container, false)

        // Get the Spinner reference
        val genderSpinner: Spinner = view.findViewById(R.id.spinnerGender)

        // Create ArrayAdapter using the string array and default spinner layout
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        )

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        genderSpinner.adapter = adapter

        // Set a listener to handle spinner item selections
        genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedGender = parent.getItemAtPosition(position).toString()
                // Handle the selected gender
                // You can perform actions based on the selected gender here
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing here
            }
        }

        return view
        //initGenderSpinner()
        //return inflater.inflate(R.layout.fragment_personal_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PersonalInfoViewModel::class.java)

        val confirmButton: Button = view.findViewById(R.id.btConfirm)

        confirmButton.setOnClickListener {
            val editNameLayout = view.findViewById<TextInputLayout>(R.id.editInformationFullName)
            val editName = editNameLayout.editText?.text.toString()

            val editAgeLayout = view.findViewById<TextInputLayout>(R.id.editInformationAge)
            val editAge = editAgeLayout.editText?.text.toString()

            val editHeightLayout = view.findViewById<TextInputLayout>(R.id.editInformationHeight)
            val editHeight = editHeightLayout.editText?.text.toString()

            val editWeightLayout = view.findViewById<TextInputLayout>(R.id.editInformationWeight)
            val editWeight = editWeightLayout.editText?.text.toString()

            viewModel.setName(editName)

            try {
                viewModel.setAge(editAge.toInt())
                viewModel.setHeight(editHeight.toDouble())
                viewModel.setWeight(editWeight.toDouble())
                viewModel.setGender(selectedGender)
            } catch (e: NumberFormatException) {
                Log.e("EditTextValues", "Error converting values to numeric types: ${e.message}")
            }

            findNavController().navigate(R.id.action_navigation_personal_info_to_setting)
        }
    }

    private fun initGenderSpinner() {
        binding.spinnerGender.isEnabled = true

        val genderSpinnerAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.gender_array, android.R.layout.simple_spinner_item
        ).also { genderSpinnerAdapter ->
            genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerGender.adapter = genderSpinnerAdapter
        }
        binding.spinnerGender.setSelection(0)

    }
}