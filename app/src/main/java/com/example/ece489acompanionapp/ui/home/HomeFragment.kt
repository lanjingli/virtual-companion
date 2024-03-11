package com.example.ece489acompanionapp.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ece489acompanionapp.R
import com.example.ece489acompanionapp.databinding.FragmentHomeBinding
import com.example.ece489acompanionapp.ui.information.PersonalInfoViewModel
import com.example.ece489acompanionapp.ui.tracker.TrackerViewModel
import java.time.LocalDate
import java.time.LocalDateTime

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val infoViewModel: PersonalInfoViewModel by activityViewModels()
    private val trackerViewModel: TrackerViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        val currentHour = LocalDateTime.now().hour
        var greetingsIndex : Int
        if (currentHour in 5..11) {
            greetingsIndex = 1
        } else if (currentHour in 12..18) {
            greetingsIndex = 2
        } else if (currentHour in 19..21) {
            greetingsIndex = 3
        } else {
            greetingsIndex = 4
        }
        val greeting = resources.getStringArray(R.array.greetings)[greetingsIndex]
        val globalname = infoViewModel.getName()
        var currentUserName = "Emily"
        if (globalname != null) {
            currentUserName = infoViewModel.getName().toString()
        }
        textView.text = greeting + " " + currentUserName

        //TODO: customize battery level text

        val numRecMet = trackerViewModel.getNumRecomMet()!!
        var mood = if (numRecMet <= 1) {
            "Angry"
        } else if (numRecMet <= 2) {
            "Ick"
        } else {
            "Happy"
        }

        binding.moodText.text = mood
        binding.pointsText.text = trackerViewModel.getTotalPoints().toString()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding?.apply {
//            btTask1.setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_tracker_water) }
//        }
//
//        binding?.apply {
//            btTask2.setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_tracker_exercise) }
//        }
//
//        binding?.apply {
//            btTask3.setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_tracker_sleep) }
//        }

        binding?.apply {
            ibCatIcon.setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_companion) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class Task(val text: String)

class TaskAdapter (
    private val tasks: MutableList<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_home_task_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}