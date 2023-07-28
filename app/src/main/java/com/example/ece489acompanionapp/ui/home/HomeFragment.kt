package com.example.ece489acompanionapp.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ece489acompanionapp.R
import com.example.ece489acompanionapp.databinding.FragmentHomeBinding
import java.time.LocalDateTime

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

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
        val currentUserName = getString(R.string.current_user_name)
        textView.text = greeting + " " + currentUserName

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}