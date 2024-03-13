package com.example.ece489acompanionapp.ui.calendar

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ece489acompanionapp.R
import com.example.ece489acompanionapp.databinding.CalendarDayBinding
import com.example.ece489acompanionapp.databinding.FragmentCalendarBinding
import com.example.ece489acompanionapp.databinding.CalendarEventItemViewBinding
import com.example.ece489acompanionapp.databinding.CalendarHeaderBinding
import com.example.ece489acompanionapp.ui.companion.CompanionViewModel
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.kizitonwose.calendar.view.ViewContainer
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.UUID

class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    private val sharedViewModel: CalendarViewModel by activityViewModels()

    private val eventsAdapter = CalendarEventsAdapter {it, type ->
        AlertDialog.Builder(requireContext())
            .setMessage(if(type) R.string.check_confirm else R.string.delete_confirm)
            .setPositiveButton(if(type) R.string.check else R.string.delete) { _, _ ->
                if(type) checkEvent(it)
                else deleteEvent(it)
            }
            .setNegativeButton(R.string.close, null)
            .show()
    }

    private val inputDialog by lazy {
        val editText = AppCompatEditText(requireContext())
        val layout = FrameLayout(requireContext()).apply {
            // Setting the padding on the EditText only pads the input area
            // not the entire EditText so we wrap it in a FrameLayout.
            val padding = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                20.toFloat(),
                requireContext().resources.displayMetrics,
            ).toInt()
            setPadding(padding, padding, padding, padding)
            addView(editText, FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ))
        }
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.calendar_task_input_title))
            .setView(layout)
            .setPositiveButton(R.string.save) { _, _ ->
                saveEvent(editText.text.toString())
                // Prepare EditText for reuse.
                editText.setText("")
            }
            .setNegativeButton(R.string.close, null)
            .create()
            .apply {
                val imm : InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                setOnShowListener {
                    // Show the keyboard
                    editText.requestFocus()

                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
                }
                setOnDismissListener {
                    // Hide the keyboard
                    imm.hideSoftInputFromWindow(view?.getWindowToken() ?: null, 0);
                }
            }
    }

    private var selectedDate: LocalDate? = null
    private val today = LocalDate.now()
    private val titleRes: Int = R.string.title_calendar
    private val maxPoints = 50

    private val titleSameYearFormatter = DateTimeFormatter.ofPattern("MMMM")
    private val titleFormatter = DateTimeFormatter.ofPattern("MMM yyyy")
    private val selectionFormatter = DateTimeFormatter.ofPattern("d MMM yyyy")
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM")
    //private val events = mutableMapOf<LocalDate, List<Event>>()

    private lateinit var binding: FragmentCalendarBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCalendarBinding.bind(view)
        binding.exThreeRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = eventsAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }

        binding.exThreeCalendar.monthScrollListener = {

            // Select the first day of the visible month.
            selectDate(it.yearMonth.atDay(1))
        }

        val daysOfWeek = daysOfWeek()
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(50)
        val endMonth = currentMonth.plusMonths(50)
        configureBinders(daysOfWeek)
        binding.exThreeCalendar.apply {
            setup(startMonth, endMonth, daysOfWeek.first())
            scrollToMonth(currentMonth)
        }

        if (savedInstanceState == null) {
            // Show today's events initially.
            binding.exThreeCalendar.post { selectDate(today) }
        }
        binding.abCalendarAddButton.setOnClickListener { inputDialog.show() }

        val currentPoints = sharedViewModel.getPoints()!!
        binding.tvPointsDisplay.text = "$currentPoints/50"
    }

    private fun saveEvent(text: String) {
        if (text.isBlank()) {
            Toast.makeText(requireContext(), R.string.calendar_task_input_empty, Toast.LENGTH_LONG)
                .show()
        } else if (text.length > 10 && text.substring(0,10) == "./tracker:") {
            Toast.makeText(requireContext(), R.string.calendar_task_input_illegal, Toast.LENGTH_LONG)
                .show()
        } else {
            selectedDate?.let {
                var events = sharedViewModel.getEvents()
                var dupe = false
                events?.get(it)?.forEach { event ->
                    if (event.text.equals(text)) dupe = true
                }
                if (dupe) {
                    Toast.makeText(requireContext(), R.string.calendar_task_input_duplicate, Toast.LENGTH_LONG)
                        .show()
                    return
                }
                events?.set(it,
                    events[it].orEmpty().plus(Event(UUID.randomUUID().toString(), text, it))
                )
                updateAdapterForDate(it)
                if (events != null) {
                    sharedViewModel.setEvents(events)
                }
            }
        }
    }

    private fun checkEvent(event: Event) {
        deleteEvent(event)
        var currentPoints = sharedViewModel.getPoints()!!
        var message: String = "Congratulations on completing a task!"
        if ((currentPoints + 10) <= maxPoints) {
            sharedViewModel.addPoints(10)
            currentPoints += 10
            message = "$message\n+10 Points.\nCurrent points: $currentPoints"
        } else {
            message =
                "$message\n+0 Points, today's points for custom tasks have already been awarded.\nCurrent points: $currentPoints"
        }
        val alertDialogBuilder = android.app.AlertDialog.Builder(requireContext())
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
        binding.tvPointsDisplay.text = "$currentPoints/50"
    }

    private fun deleteEvent(event: Event) {
        val date = event.date
        var events = sharedViewModel.getEvents()
        events?.set(date, events[date].orEmpty().minus(event))
        updateAdapterForDate(date)
        if (events != null) {
            sharedViewModel.setEvents(events)
        }
    }

    private fun updateAdapterForDate(date: LocalDate) {
        val nonNullEvents: MutableMap<LocalDate, List<Event>> = sharedViewModel.getEvents() ?: mutableMapOf<LocalDate, List<Event>>()
        eventsAdapter.apply {
            events.clear()
            events.addAll(nonNullEvents[date].orEmpty())
            notifyDataSetChanged()
        }
        binding.exThreeSelectedDateText.text = selectionFormatter.format(date)
        binding.tvCalendarMonth.text = monthTitleFormatter.format(date)
        if (date.compareTo(today) < 0) {
            binding.abCalendarAddButton.visibility = View.INVISIBLE
        } else {
            binding.abCalendarAddButton.visibility = View.VISIBLE
        }
    }

    private fun selectDate(date: LocalDate) {
        if (selectedDate != date) {
            val oldDate = selectedDate
            selectedDate = date
            oldDate?.let { binding.exThreeCalendar.notifyDateChanged(it) }
            binding.exThreeCalendar.notifyDateChanged(date)
            updateAdapterForDate(date)
        }
    }

    private fun configureBinders(daysOfWeek: List<DayOfWeek>) {
        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val binding = CalendarDayBinding.bind(view)

            init {
                view.setOnClickListener {
                    if (day.position == DayPosition.MonthDate) {
                        selectDate(day.date)
                    }
                }
            }
        }
        binding.exThreeCalendar.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.day = data
                val textView = container.binding.tvCalendarDayText
                val dotView = container.binding.vCalendarDotView

                textView.text = data.date.dayOfMonth.toString()

                if (data.position == DayPosition.MonthDate) {
                    textView.visibility = View.VISIBLE
                    when (data.date) {
                        today -> {
                            context?.let { textView.setTextColor(it.getColor(R.color.white)) }
                            textView.setBackgroundResource(R.drawable.ic_oval)
                            dotView.visibility = View.INVISIBLE
                        }
                        selectedDate -> {
                            context?.let { textView.setTextColor(it.getColor(androidx.appcompat.R.color.primary_text_default_material_dark)) }
                            textView.setBackgroundResource(R.drawable.ic_oval_alt)
                            dotView.visibility = View.INVISIBLE
                        }
                        else -> {
                            if (today.compareTo(data.date) < 0) {
                                context?.let { textView.setTextColor(it.getColor(R.color.black))}
                            } else {
                                context?.let { textView.setTextColor(it.getColor(R.color.grey_600))}
                            }
                            textView.background = null
                            var events = sharedViewModel.getEvents()
                            dotView.isVisible = events?.get(data.date).orEmpty().isNotEmpty()
                        }
                    }
                } else {
                    textView.visibility = View.INVISIBLE
                    dotView.visibility = View.INVISIBLE
                }
            }
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val legendLayout = CalendarHeaderBinding.bind(view).legendLayout.root
        }
        binding.exThreeCalendar.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View) = MonthViewContainer(view)
                override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                    // Setup each header day text if we have not done that already.
                    if (container.legendLayout.tag == null) {
                        container.legendLayout.tag = data.yearMonth
                        container.legendLayout.children.map { it as TextView }
                            .forEachIndexed { index, tv ->
                                tv.text = daysOfWeek[index].name.first().toString()
                                context?.let { tv.setTextColor(it.getColor(R.color.black)) }
                            }
                    }
                }
            }
    }
}

data class Event(val id: String, val text: String, val date: LocalDate)

class CalendarEventsAdapter(val onClick: (Event, Boolean) -> Unit) :
    RecyclerView.Adapter<CalendarEventsAdapter.CalendarViewHolder>() {
    val events = mutableListOf<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(
            CalendarEventItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(viewHolder: CalendarViewHolder, position: Int) {
        viewHolder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size
    inner class CalendarViewHolder(private val binding: CalendarEventItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btCheckEvent.setOnClickListener {
                onClick(events[bindingAdapterPosition], true)
            }
            binding.btDeleteEvent.setOnClickListener {
                onClick(events[bindingAdapterPosition], false)
            }
        }

        fun bind(event: Event) {
            binding.itemEventText.text = event.text
        }
    }
}

