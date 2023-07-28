package com.example.ece489acompanionapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ece489acompanionapp.R
import android.R as androidR
import com.example.ece489acompanionapp.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null

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

        initTrackerSpinners()
        initTrackerCheckboxes()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            waterCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                waterSpinner.isEnabled = isChecked
            }

            sleepCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                sleepSpinner.isEnabled = isChecked
            }

            meditationCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                meditationSpinner.isEnabled = isChecked
            }

            exerciseCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                exerciseSpinner.isEnabled = isChecked
            }

            foodCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                foodSpinner.isEnabled = isChecked
            }

            substanceAbuseCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                substanceAbuseSpinner.isEnabled = isChecked
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
            R.array.planets_array, androidR.layout.simple_spinner_item
        ).also { waterSpinnerAdapter ->
            waterSpinnerAdapter.setDropDownViewResource(androidR.layout.simple_spinner_dropdown_item)
            binding.waterSpinner.adapter = waterSpinnerAdapter
        }
        binding.waterSpinner.setSelection(0)

        //init sleep spinner
        val sleepSpinnerAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.planets_array, androidR.layout.simple_spinner_item
        ).also { sleepSpinnerAdapter ->
            sleepSpinnerAdapter.setDropDownViewResource(androidR.layout.simple_spinner_dropdown_item)
            binding.sleepSpinner.adapter = sleepSpinnerAdapter
        }
        binding.sleepSpinner.setSelection(0)

        //init meditation spinner
        val meditationSpinnerAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.planets_array, androidR.layout.simple_spinner_item
        ).also { meditationSpinnerAdapter ->
            meditationSpinnerAdapter.setDropDownViewResource(androidR.layout.simple_spinner_dropdown_item)
            binding.meditationSpinner.adapter = meditationSpinnerAdapter
        }
        binding.meditationSpinner.setSelection(0)

        //init exercise spinner
        val exerciseSpinnerAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.planets_array, androidR.layout.simple_spinner_item
        ).also { exerciseSpinnerAdapter ->
            exerciseSpinnerAdapter.setDropDownViewResource(androidR.layout.simple_spinner_dropdown_item)
            binding.exerciseSpinner.adapter = exerciseSpinnerAdapter
        }
        binding.exerciseSpinner.setSelection(0)

        //init food spinner
        val foodSpinnerAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.planets_array, androidR.layout.simple_spinner_item
        ).also { foodSpinnerAdapter ->
            foodSpinnerAdapter.setDropDownViewResource(androidR.layout.simple_spinner_dropdown_item)
            binding.foodSpinner.adapter = foodSpinnerAdapter
        }
        binding.foodSpinner.setSelection(0)

        // init substance abuse spinner
        val substanceAbuseSpinnerAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.planets_array, androidR.layout.simple_spinner_item
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