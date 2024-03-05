package com.example.ece489acompanionapp.ui.companion

import android.annotation.SuppressLint
import android.widget.BaseAdapter
import android.content.Context
import android.widget.TextView
import android.widget.ImageView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.ece489acompanionapp.R

internal class DecoratorGridAdapter(
    private val decoratorList: List<DecoratorModel>,
    private val context: Context
): BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var decoratorTextView: TextView
    private lateinit var decoratorImageView: ImageView
    private var selectedPosition: Int = -1

    // return count of decorator list
    override fun getCount(): Int {
        return decoratorList.size
    }

    // return the item at position of grid view
    override fun getItem(position: Int): Any? {
        return null
    }

    // return item id of grid view
    override fun getItemId(position: Int): Long {
        return 0
    }

    fun setSelectedPosition (position: Int): Void? {
        selectedPosition = position
        return null

    }

    @SuppressLint("ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView

        // if layoutinflater is null, set it
        if (layoutInflater == null) {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        }

        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.decorator_gridview_item, null)
        }

        // Initialize grid view
        decoratorImageView = convertView!!.findViewById(R.id.DecoImageView)
        decoratorTextView = convertView!!.findViewById(R.id.DecoTextView)

        // Setting custom image
        decoratorImageView.setImageResource(decoratorList[position].decoratorImage)
        decoratorTextView.setText(decoratorList[position].decoratorName)

        if (position == selectedPosition) {
            convertView as CardView
            convertView.setCardBackgroundColor(ContextCompat.getColor(convertView.getContext(), R.color.grey_400))
        } else {
            convertView as CardView
            convertView.setCardBackgroundColor(ContextCompat.getColor(convertView.getContext(), com.google.android.material.R.color.mtrl_btn_transparent_bg_color))
        }

        return convertView

    }

}