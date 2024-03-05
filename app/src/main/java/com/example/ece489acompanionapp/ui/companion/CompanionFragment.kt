package com.example.ece489acompanionapp.ui.companion

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ece489acompanionapp.R
import com.example.ece489acompanionapp.databinding.FragmentCompanionBinding

class CompanionFragment : Fragment(0) {

    private var _binding: FragmentCompanionBinding? = null
    private val sharedViewModel: CompanionViewModel by activityViewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val pointToDeco = mapOf(
        0 to "Default",
        5 to "Santa Hat",
        10 to "Fun Glasses",
        20 to "Crown",
        30 to "Black Frame Glasses",
        50 to "Magic Hat",
        70 to "Swag Glasses",
        100 to "Graduation Hat",
        130 to "Mustache"
    )

    private val decoToImage = mapOf(
        "Default Default" to R.drawable.cat,
        "Default Fun Glasses" to R.drawable.cat_sea_glasses,
        "Default Black Frame Glasses" to R.drawable.cat_black_frame_glasses,
        "Default Swag Glasses" to R.drawable.cat_swag_glasses,
        "Default Mustache" to R.drawable.cat_mustache,

        "Santa Hat Default" to R.drawable.cat_santa,
        "Santa Hat Fun Glasses" to R.drawable.cat_santa_sea_glasses,
        "Santa Hat Black Frame Glasses" to R.drawable.cat_santa_black_frame_glasses,
        "Santa Hat Swag Glasses" to R.drawable.cat_santa_swag_glasses,
        "Santa Hat Mustache" to R.drawable.cat_santa_mustache,

        "Crown Default" to R.drawable.cat_crown,
        "Crown Fun Glasses" to R.drawable.cat_crown_sea_glasses,
        "Crown Black Frame Glasses" to R.drawable.cat_crown_black_frame_glasses,
        "Crown Swag Glasses" to R.drawable.cat_crown_swag_glasses,
        "Crown Mustache" to R.drawable.cat_crown_mustache,

        "Magic Hat Default" to R.drawable.cat_magic,
        "Magic Hat Fun Glasses" to R.drawable.cat_magic_sea_glasses,
        "Magic Hat Black Frame Glasses" to R.drawable.cat_magic_black_frame_glasses,
        "Magic Hat Swag Glasses" to R.drawable.cat_magic_swag_glasses,
        "Magic Hat Mustache" to R.drawable.cat_magic_mustache,

        "Graduation Hat Default" to R.drawable.cat_grad,
        "Graduation Hat Fun Glasses" to R.drawable.cat_grad_sea_glasses,
        "Graduation Hat Black Frame Glasses" to R.drawable.cat_grad_black_frame_glasses,
        "Graduation Hat Swag Glasses" to R.drawable.cat_grad_swag_glasses,
        "Graduation Hat Mustache" to R.drawable.cat_grad_mustache
    )

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompanionBinding.inflate(inflater, container, false)

        val headDecoratorText = binding.titleHeadDecorator
        val selectedHeadDeco = sharedViewModel.getHeadDecoratorSelected()
        headDecoratorText.text = "Head Decorator: $selectedHeadDeco"

        val faceDecoratorText = binding.titleFaceDecorator
        val selectedFaceDeco = sharedViewModel.getFaceDecoratorSelected()
        faceDecoratorText.text = "Face Decorator: $selectedFaceDeco"

        val companionImageView = binding.companionImage
        val key = "$selectedHeadDeco $selectedFaceDeco"
        companionImageView.setImageResource(decoToImage[key]!!)

        // TODO: get user points from database
        val userPoint = 23
        val tmpMap = pointToDeco.filterKeys { it > userPoint }
        val keyList = tmpMap.keys.toList()
        val goalKey = keyList[0]
        val goalValue = pointToDeco[goalKey]
        val toReachGoal = goalKey - userPoint

        binding.textProgress.text = "$userPoint pts"
        binding.textProgressGoal.text = "You need $toReachGoal points to unlock $goalValue"


        // TODO: track user goal completion consecutive days
        val missedGoalDays = 3
        val companionProgressTextView = binding.textCompanionProgress
        if (container!=null) {
            val healthyColor = ContextCompat.getColor(container.getContext(), R.color.green_400)
            val ickColor = ContextCompat.getColor(container.getContext(), R.color.orange_300)
            val sickColor = ContextCompat.getColor(container.getContext(), R.color.red_10)
            if (missedGoalDays <= 2) {
                companionImageView.setBackgroundColor(healthyColor)
                companionProgressTextView.text = "Your companion is currently health\nKeep up the good work!"
                companionProgressTextView.setTextColor(healthyColor)
            } else if (missedGoalDays <= 6) {
                companionImageView.setBackgroundColor((ickColor))
                companionProgressTextView.text = "Your companion is starting to feel ill\nLet's try to reach our goal tomorrow!"
                companionProgressTextView.setTextColor(ickColor)
            } else {
                companionImageView.setBackgroundColor((sickColor))
                companionProgressTextView.text = "Your companion is very sick\nWe will definitely reach our goal tomorrow!"
                companionProgressTextView.setTextColor(sickColor)
            }
        }

        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            headDecoratorButton.setOnClickListener { goToHeadDecoratorScreen() }
        }

        binding.apply {
            faceDecoratorButton.setOnClickListener { goToFaceDecoratorScreen() }
        }
    }

    fun goToHeadDecoratorScreen() {
        findNavController().navigate(R.id.action_navigation_companion_to_companion_head_decoration)
    }

    fun goToFaceDecoratorScreen() {
        findNavController().navigate(R.id.action_navigation_companion_to_companion_face_decoration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}