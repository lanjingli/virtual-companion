package com.example.ece489acompanionapp.ui.notifications

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil.setContentView
import com.example.ece489acompanionapp.R
import com.example.ece489acompanionapp.databinding.FragmentNotificationsBinding
import java.util.Calendar
import java.util.Date
import android.Manifest
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat

class NotificationsFragment : Fragment() {
    //private lateinit var viewModel: NotificationsViewModel
    private lateinit var binding : FragmentNotificationsBinding
    private val ALARM_PERMISSION = "android.permission.SCHEDULE_EXACT_ALARM"
    private val PERMISSION_REQUEST_CODE = 123

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val title: TextView = root.findViewById(R.id.title)
        title.text = "Herbert Companion App Reminder"

        val message: TextView = root.findViewById(R.id.message)
        val trackerPage = arguments?.getString("trackerPage")
        message.text = "Herbert reminds you to complete your " + trackerPage + " goal!"

        createNotificationChannel()
        binding.submitButton.setOnClickListener{ scheduleNotification() }

        return root
    }

    private fun scheduleNotification() {
        val context: Context = requireContext()
        val permission = Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS

        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Request the permission
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(permission),
                PERMISSION_REQUEST_CODE
            )
        } else {
            try {
                val context: Context = requireContext()
                val intent = Intent(context, Notification::class.java)
                val title = binding.title.text.toString()
                val message = binding.message.text.toString()
                intent.putExtra(titleExtra, title)
                intent.putExtra(messageExtra, message)

                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    notificationID,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )

                val alarmManager =
                    requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val time = getTime()
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    time,
                    pendingIntent
                )
                showAlert(time, title, message)
            } catch (e: Exception) {
                Log.e("NotificationsFragment", "Error: ${e.message}", e)
            }
        }
    }

    private fun showAlert(time: Long, title: String, message: String) {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(context)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(context)

        AlertDialog.Builder(requireContext())
            .setTitle("Notification Scheduled")
            .setMessage(
                "Title: " + title + "\nMessage: " + message + "\nAt: " + dateFormat.format(date) + " " + timeFormat.format(date)
            )
            .setPositiveButton("Okay"){_,_ ->}
            .show()
    }

    private fun getTime(): Long {
        val minute = binding.timePicker.minute
        val hour = binding.timePicker.hour
        val day = binding.datePicker.dayOfMonth
        val month = binding.datePicker.month
        val year = binding.datePicker.year

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with scheduling
                scheduleNotification()
            } else {
                // Permission denied
                showPermissionDeniedDialog()
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permission Denied")
            .setMessage("The app needs permission to set exact alarms for scheduling notifications.")
            .setPositiveButton("OK") { _, _ ->
                // You can add further actions if needed
            }
            .setNegativeButton("Cancel") { _, _ ->
                // You can add further actions if needed
            }
            .show()
    }

    private fun hasAlarmPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            ALARM_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }
}