package com.example.currencyconverter.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyconverter.R
import com.example.currencyconverter.controller.CurrencyAdapter
import com.example.currencyconverter.controller.DataController
import com.example.currencyconverter.databinding.ActivityCurrencySelectionBinding

class CurrencySelection : AppCompatActivity() {
    private val currencyAdapter = CurrencyAdapter(DataController.liveData.value)
    private lateinit var binding: ActivityCurrencySelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_currency_selection)

        currencyAdapter.setIntent(intent)

        val layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, layoutManager.orientation)

        binding.currencyList.layoutManager = layoutManager
        binding.currencyList.addItemDecoration(decoration)
        binding.currencyList.adapter = currencyAdapter
    }

    fun setCurrency(view: View) {
        val index = view.findViewById<TextView>(R.id.index).text.toString()

        val intent = Intent(this, CurrencyConverter::class.java)
        intent.putExtra("index", index)
        setResult(RESULT_OK, intent)

        finish()
    }

    override fun onBackPressed() {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.currency_selection, menu)

        return super.onCreateOptionsMenu(menu)
    }

    fun onBackPressed(@Suppress("UNUSED_PARAMETER") item: MenuItem) {
        super.onBackPressed()
    }
}