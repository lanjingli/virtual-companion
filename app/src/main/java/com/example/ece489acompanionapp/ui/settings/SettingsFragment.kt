package com.example.ece489acompanionapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ece489acompanionapp.R
import android.R as androidR
import com.example.ece489acompanionapp.databinding.FragmentSettingsBinding
import com.example.ece489acompanionapp.ui.information.PersonalInfoViewModel

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val sharedViewModel: SettingsViewModel by activityViewModels()
    private val  infoViewModel: PersonalInfoViewModel by activityViewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initPersonalInformation()
        initTrackerSpinners()
        initTrackerCheckboxes()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            waterCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                waterSpinner.isEnabled = isChecked
                sharedViewModel.setWaterTrackerEnabled(isChecked)
            }

            sleepCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                sleepSpinner.isEnabled = isChecked
                sharedViewModel.setSleepTrackerEnabled(isChecked)
            }

            meditationCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                meditationSpinner.isEnabled = isChecked
                sharedViewModel.setMeditationTrackerEnabled(isChecked)
            }

            exerciseCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                exerciseSpinner.isEnabled = isChecked
                sharedViewModel.setExerciseTrackerEnabled(isChecked)
            }

            foodCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                foodSpinner.isEnabled = isChecked
                sharedViewModel.setFoodTrackerEnabled(isChecked)
            }

            substanceAbuseCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                substanceAbuseSpinner.isEnabled = isChecked
                sharedViewModel.setSubstanceAbuseTrackerEnabled(isChecked)
            }

            waterSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                var str = ""
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    str = parent?.getItemAtPosition(position).toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }

        binding?.apply {
            btSettingsEditPersonalInformation.setOnClickListener { findNavController().navigate(R.id.action_navigation_setting_to_personal_info) }
        }
    }

    private fun initPersonalInformation() {
        infoViewModel.getName()?.let { name -> binding.titleName.text = name.toString() }
        infoViewModel.getAge()?.let { age -> binding.valueAge.text = age.toString() }
        infoViewModel.getHeight()?.let { height -> binding.valueHeight.text = height.toString() }
        infoViewModel.getWeight()?.let { weight -> binding.valueWeight.text = weight.toString() }
        infoViewModel.getGender()?.let { gender -> binding.valueGender.text = gender.toString() }
    }

    private fun initTrackerCheckboxes() {
        binding.waterCheckbox.isChecked = true
        binding.sleepCheckbox.isChecked = true
        binding.meditationCheckbox.isChecked = true
        binding.exerciseCheckbox.isChecked = true
        binding.foodCheckbox.isChecked = true
        binding.substanceAbuseCheckbox.isChecked = true
    }

    private fun initTrackerSpinners() {
        binding.waterSpinner.isEnabled = true
        binding.sleepSpinner.isEnabled = true
        binding.meditationSpinner.isEnabled = true
        binding.exerciseSpinner.isEnabled = true
        binding.foodSpinner.isEnabled = true
        binding.substanceAbuseSpinner.isEnabled = true

        //init water spinner
        val waterSpinnerAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.water_array, androidR.layout.simple_spinner_item
        ).also { waterSpinnerAdapter ->
            waterSpinnerAdapter.setDropDownViewResource(androidR.layout.simple_spinner_dropdown_item)
            binding.waterSpinner.adapter = waterSpinnerAdapter
        }
        binding.waterSpinner.setSelection(0)

        //init sleep spinner
        val sleepSpinnerAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.sleep_array, androidR.layout.simple_spinner_item
        ).also { sleepSpinnerAdapter ->
            sleepSpinnerAdapter.setDropDownViewResource(androidR.layout.simple_spinner_dropdown_item)
            binding.sleepSpinner.adapter = sleepSpinnerAdapter
        }
        binding.sleepSpinner.setSelection(0)

        //init meditation spinner
        val meditationSpinnerAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.meditation_array, androidR.layout.simple_spinner_item
        ).also { meditationSpinnerAdapter ->
            meditationSpinnerAdapter.setDropDownViewResource(androidR.layout.simple_spinner_dropdown_item)
            binding.meditationSpinner.adapter = meditationSpinnerAdapter
        }
        binding.meditationSpinner.setSelection(0)

        //init exercise spinner
        val exerciseSpinnerAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.exercise_array, androidR.layout.simple_spinner_item
        ).also { exerciseSpinnerAdapter ->
            exerciseSpinnerAdapter.setDropDownViewResource(androidR.layout.simple_spinner_dropdown_item)
            binding.exerciseSpinner.adapter = exerciseSpinnerAdapter
        }
        binding.exerciseSpinner.setSelection(0)

        //init food spinner
        val foodSpinnerAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.food_array, androidR.layout.simple_spinner_item
        ).also { foodSpinnerAdapter ->
            foodSpinnerAdapter.setDropDownViewResource(androidR.layout.simple_spinner_dropdown_item)
            binding.foodSpinner.adapter = foodSpinnerAdapter
        }
        binding.foodSpinner.setSelection(0)

        // init substance abuse spinner
        val substanceAbuseSpinnerAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.substance_abuse_array, androidR.layout.simple_spinner_item
        ).also { substanceAbuseSpinnerAdapter ->
            substanceAbuseSpinnerAdapter.setDropDownViewResource(androidR.layout.simple_spinner_dropdown_item)
            binding.substanceAbuseSpinner.adapter = substanceAbuseSpinnerAdapter
        }
        binding.substanceAbuseSpinner.setSelection(0)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}