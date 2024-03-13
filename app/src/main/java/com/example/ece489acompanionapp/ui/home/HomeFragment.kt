package com.example.ece489acompanionapp.ui.home

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ece489acompanionapp.R
import com.example.ece489acompanionapp.databinding.CalendarEventItemViewBinding
import com.example.ece489acompanionapp.databinding.FragmentHomeBinding
import com.example.ece489acompanionapp.ui.information.PersonalInfoViewModel
import com.example.ece489acompanionapp.ui.tracker.TrackerViewModel
import com.example.ece489acompanionapp.databinding.FragmentHomeTaskCardBinding
import com.example.ece489acompanionapp.ui.calendar.CalendarEventsAdapter
import com.example.ece489acompanionapp.ui.calendar.CalendarViewModel
import com.example.ece489acompanionapp.ui.calendar.Event
import com.example.ece489acompanionapp.ui.settings.SettingsViewModel
import java.time.LocalDate
import java.time.LocalDateTime

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val infoViewModel: PersonalInfoViewModel by activityViewModels()
    private val trackerViewModel: TrackerViewModel by activityViewModels()
    private val calendarViewModel: CalendarViewModel by activityViewModels()
    private val settingsViewModel: SettingsViewModel by activityViewModels()

//    private val taskAdapter = TaskAdapter()
    private lateinit var taskAdapter: TaskAdapter

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

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireContext()
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

        val moods = mapOf("Angry" to ContextCompat.getColor(context, R.color.red_10),
            "Ick" to ContextCompat.getColor(context, R.color.orange_300),
            "Happy" to ContextCompat.getColor(context, R.color.green_400))
        val numRecMet = trackerViewModel.getNumRecomMet()!!
        var mood = if (numRecMet <= 1) {
            "Angry"
        } else if (numRecMet <= 2) {
            "Ick"
        } else {
            "Happy"
        }
        binding.moodText.setTextColor(moods.get(mood)!!)
        val coloredDrawable =
            ContextCompat.getDrawable(context, R.drawable.ic_home_green_24dp)
                ?.let { setDrawableColor(it, moods.get(mood)!!) }
        binding.moodIcon.setImageDrawable(coloredDrawable)

        binding.moodText.text = mood
        binding.pointsText.text = (trackerViewModel.getTotalPoints()!! + calendarViewModel.getPoints()!!).toString()
        binding.ibCatIcon.setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_companion) }

        taskAdapter = TaskAdapter(requireContext())
        taskAdapter.tasks.addAll(getTrackerTasks())
        binding.rvHomeCards.adapter = taskAdapter
        binding.rvHomeCards.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()
        val events: MutableMap<LocalDate, List<Event>> = calendarViewModel.getEvents() ?: mutableMapOf<LocalDate, List<Event>>()
        taskAdapter.apply {
            tasks.clear()
            tasks.addAll(events[LocalDate.now()].orEmpty().map { Task(it.text) })
            tasks.addAll(getTrackerTasks())
            notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getTrackerTasks() : List<Task>{
        val trackerTaskIndicator : String = "./tracker:"
        var tasks = mutableListOf<Task>()
        if (settingsViewModel.getWaterTrackerEnabled() == true) {
            tasks.add(Task(trackerTaskIndicator + 'w'))
        }
        if (settingsViewModel.getSleepTrackerEnabled() == true) {
            tasks.add(Task(trackerTaskIndicator + 's'))
        }
        if (settingsViewModel.getMeditationTrackerEnabled() == true) {
            tasks.add(Task(trackerTaskIndicator + 'm'))
        }
        if (settingsViewModel.getExerciseTrackerEnabled() == true) {
            tasks.add(Task(trackerTaskIndicator + 'e'))
        }
        if (settingsViewModel.getFoodTrackerEnabled() == true) {
            tasks.add(Task(trackerTaskIndicator + 'f'))
        }
        if (settingsViewModel.getSubstanceAbuseTrackerEnabled() == true) {
            tasks.add(Task(trackerTaskIndicator + 'r'))
        }
        return tasks
    }
}

fun setDrawableColor(drawable: Drawable, color: Int): Drawable {
    val wrappedDrawable = DrawableCompat.wrap(drawable)
    DrawableCompat.setTint(wrappedDrawable, color)
    return wrappedDrawable
}

data class Task(val text: String)

class TaskAdapter (
    val context: Context
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    val tasks = mutableListOf<Task>()
    inner class TaskViewHolder(private val binding: FragmentHomeTaskCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            if (task.text.length >= 11 && task.text.substring(0,10) == "./tracker:") {
                when(task.text.substring(10)) {
                    "w" -> {
                        val coloredDrawable = ContextCompat.getDrawable(context, R.drawable.ic_water_empty_48dp)
                            ?.let { setDrawableColor(it, ContextCompat.getColor(context, R.color.white)) }
                        binding.btTask.apply {
                            text = "stay hydrated"
                            setTextColor(ContextCompat.getColor(context, R.color.white))
                            setCompoundDrawablesWithIntrinsicBounds(coloredDrawable, null, null, null);
                            setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_tracker_water) }
                        }
                        binding.cvTask.setCardBackgroundColor(ContextCompat.getColor(context, R.color.teal_700)) //teal_700
                    }
                    "s" -> {
                        val coloredDrawable = ContextCompat.getDrawable(context, R.drawable.ic_bed_empty_48dp)
                            ?.let { setDrawableColor(it, ContextCompat.getColor(context, R.color.white)) }
                        binding.btTask.apply {
                            text = "rest on time"
                            setTextColor(ContextCompat.getColor(context, R.color.white))
                            setCompoundDrawablesWithIntrinsicBounds(coloredDrawable, null, null, null);
                            setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_tracker_sleep) }
                        }
                        binding.cvTask.setCardBackgroundColor(ContextCompat.getColor(context, R.color.purple_700)) //purple_700
                    }
                    "m" -> {
                        val coloredDrawable = ContextCompat.getDrawable(context, R.drawable.ic_meditation_empty_48dp)
                            ?.let { setDrawableColor(it, ContextCompat.getColor(context, R.color.white)) }
                        binding.btTask.apply {
                            text = "meditate"
                            setTextColor(ContextCompat.getColor(context, R.color.white))
                            setCompoundDrawablesWithIntrinsicBounds(coloredDrawable, null, null, null);
                            setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_tracker_meditation) }
                        }
                        binding.cvTask.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green_600)) //green_600
                    }
                    "e" -> {
                        val coloredDrawable = ContextCompat.getDrawable(context, R.drawable.ic_exercise_48dp)
                            ?.let { setDrawableColor(it, ContextCompat.getColor(context, R.color.white)) }
                        binding.btTask.apply {
                            text = "exercise"
                            setTextColor(ContextCompat.getColor(context, R.color.white))
                            setCompoundDrawablesWithIntrinsicBounds(coloredDrawable, null, null, null);
                            setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_tracker_exercise) }
                        }
                        binding.cvTask.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red_10)) //red_10
                    }
                    "f" -> {
                        val coloredDrawable = ContextCompat.getDrawable(context, R.drawable.ic_burger_empty_48dp)
                            ?.let { setDrawableColor(it, ContextCompat.getColor(context, R.color.white)) }
                        binding.btTask.apply {
                            text = "eat healthy"
                            setTextColor(ContextCompat.getColor(context, R.color.white))
                            setCompoundDrawablesWithIntrinsicBounds(coloredDrawable, null, null, null);
                            setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_tracker_food) }
                        }
                        binding.cvTask.setCardBackgroundColor(ContextCompat.getColor(context, R.color.orange_300)) //orange_300
                    }
                    "r" -> {
                        val coloredDrawable = ContextCompat.getDrawable(context, R.drawable.ic_block_24)
                            ?.let { setDrawableColor(it, ContextCompat.getColor(context, R.color.white)) }
                        binding.btTask.apply {
                            text = "rehabilitate"
                            setTextColor(ContextCompat.getColor(context, R.color.white))
                            setCompoundDrawablesWithIntrinsicBounds(coloredDrawable, null, null, null);
                            setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_tracker_substance_abuse) }
                        }
                        binding.cvTask.setCardBackgroundColor(ContextCompat.getColor(context, R.color.black)) //black
                    }
                }
            } else {
                val coloredDrawable = ContextCompat.getDrawable(context, R.drawable.ic_calendar_black_24dp)
                    ?.let { setDrawableColor(it, ContextCompat.getColor(context, R.color.white)) }
                binding.btTask.apply {
                    text = task.text
                    setTextColor(ContextCompat.getColor(context, R.color.white))
                    setCompoundDrawablesWithIntrinsicBounds(coloredDrawable, null, null, null);
                    setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_calendar) }
                }
                binding.cvTask.setCardBackgroundColor(ContextCompat.getColor(context, R.color.grey_600)) //grey_600
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            FragmentHomeTaskCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}