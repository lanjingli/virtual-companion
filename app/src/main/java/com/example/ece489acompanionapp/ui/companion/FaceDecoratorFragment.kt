package com.example.ece489acompanionapp.ui.companion

import com.example.ece489acompanionapp.databinding.FragmentCompanionFaceDecoratorBinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ece489acompanionapp.R

class FaceDecoratorFragment : Fragment() {

    private var _binding: FragmentCompanionFaceDecoratorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var decoratorGridView: GridView
    lateinit var decoratorList: List<DecoratorModel>
    private val sharedViewModel: CompanionViewModel by activityViewModels()
    private val pointToDeco = mapOf(
        0 to "Default",
        10 to "Fun Glasses",
        30 to "Black Frame Glasses",
        70 to "Swag Glasses",
        130 to "Mustache"
    )
    private val decoToImage = mapOf(
        "Default" to R.drawable.ic_block_24,
        "Fun Glasses" to R.drawable.sea_glasses,
        "Black Frame Glasses" to R.drawable.black_frame_glasses,
        "Swag Glasses" to R.drawable.swag_glasses,
        "Mustache" to R.drawable.mustache
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompanionFaceDecoratorBinding.inflate(inflater, container, false)

        decoratorGridView = binding.faceDecoratorGRV
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

        val faceDecoratorAdapter =
            container?.let { DecoratorGridAdapter(decoratorList = decoratorList, it.getContext()) }

        decoratorGridView.adapter = faceDecoratorAdapter

        var selectedPosition = 0
        // change cardview background color when selected
        decoratorGridView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, v, position, id ->
            faceDecoratorAdapter?.setSelectedPosition(position)
            faceDecoratorAdapter?.notifyDataSetChanged()
            selectedPosition = position
        })
        val saveButton = binding.save
        saveButton.setOnClickListener {
            sharedViewModel.saveFaceDecoratorSelected(myList[selectedPosition])
            findNavController().navigate(R.id.action_companion_face_decoration_to_navigation_companion)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}