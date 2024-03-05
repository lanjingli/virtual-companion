package com.example.ece489acompanionapp.ui.companion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ece489acompanionapp.R
import com.example.ece489acompanionapp.databinding.FragmentCompanionHeadDecoratorBinding

class HeadDecoratorFragment : Fragment() {

    private var _binding: FragmentCompanionHeadDecoratorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var decoratorGridView: GridView
    lateinit var decoratorList: List<DecoratorModel>
    private val sharedViewModel: CompanionViewModel by activityViewModels()
    private val pointToDeco = mapOf(
        0 to "Default",
        5 to "Santa Hat",
        20 to "Crown",
        50 to "Magic Hat",
        100 to "Graduation Hat"
    )
    private val decoToImage = mapOf(
        "Default" to R.drawable.ic_block_24,
        "Santa Hat" to R.drawable.santa,
        "Crown" to R.drawable.crown,
        "Magic Hat" to R.drawable.magic,
        "Graduation Hat" to R.drawable.grad
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCompanionHeadDecoratorBinding.inflate(inflater, container, false)

        decoratorGridView = binding.headDecoratorGRV
        decoratorList = ArrayList<DecoratorModel>()

        // TODO: get user points from database
        val userPoint = 23
        var myList = mutableListOf<String>()
        pointToDeco.forEach { (key, value) ->
            // user point is enough to unlock this deco, add to decorator list
            if (key <= userPoint) {
                decoratorList = decoratorList + DecoratorModel(value, decoToImage[value]!!)
                myList.add(value)
            } else {
                val pointsRequired = key - userPoint
                val lockedDecoName = "$pointsRequired Points Required to unlock"
                decoratorList = decoratorList + DecoratorModel(lockedDecoName, R.drawable.ic_question_mark_24)
                myList.add("Default")
            }
        }

        val headDecoratorAdapter =
            container?.let { DecoratorGridAdapter(decoratorList = decoratorList, it.getContext()) }

        decoratorGridView.adapter = headDecoratorAdapter

        var selectedPosition = 0
        // change cardview background color when selected
        decoratorGridView.setOnItemClickListener(OnItemClickListener { parent, v, position, id ->
            headDecoratorAdapter?.setSelectedPosition(position)
            headDecoratorAdapter?.notifyDataSetChanged()
            selectedPosition = position
        })

        val saveButton = binding.save
        saveButton.setOnClickListener {
            sharedViewModel.saveHeadDecoratorSelected(myList[selectedPosition])
            findNavController().navigate(R.id.action_companion_head_decoration_to_navigation_companion)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}