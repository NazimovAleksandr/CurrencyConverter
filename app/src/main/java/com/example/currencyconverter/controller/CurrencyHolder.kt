package com.example.currencyconverter.controller

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import java.util.ArrayList

class CurrencyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val currencyName: TextView = itemView.findViewById(R.id.currencyName)
    private val charCode: TextView = itemView.findViewById(R.id.charCode)
    private var index: TextView = itemView.findViewById(R.id.index)
    private var image: ImageView = itemView.findViewById(R.id.selected)

    fun bind(
        currency: ArrayList<String>,
        position: Int,
        selectedCurrencyIndex1: Int?,
        selectedCurrencyIndex2: Int?,
    ) {
        currencyName.text = currency[3]
        charCode.text = currency[1]
        index.text = position.toString()

        if (position.toString().toInt() == selectedCurrencyIndex1 || position.toString().toInt() == selectedCurrencyIndex2) {
            image.visibility = View.VISIBLE
        } else {
            image.visibility = View.INVISIBLE
        }
    }
}
