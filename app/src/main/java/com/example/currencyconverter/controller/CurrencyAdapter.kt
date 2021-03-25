package com.example.currencyconverter.controller

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R

class CurrencyAdapter(
    private val value: ArrayList<ArrayList<String>>?,
) : RecyclerView.Adapter<CurrencyHolder>() {

    private var selectedCurrencyIndex1: Int? = 10
    private var selectedCurrencyIndex2: Int? = 11

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val context = parent.context
        val inflater =  LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.currency, parent, false)

        return CurrencyHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val currency = value!![position]

        holder.bind(currency, position, selectedCurrencyIndex1, selectedCurrencyIndex2)
    }

    override fun getItemCount() = value!!.size

    fun setIntent(intent: Intent?) {
        selectedCurrencyIndex1 = intent?.getIntExtra("selected1", 10)
        selectedCurrencyIndex2 = intent?.getIntExtra("selected2", 11)
    }
}