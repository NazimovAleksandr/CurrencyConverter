package com.example.currencyconverter.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.controller.CurrencyAdapter
import com.example.currencyconverter.controller.DataController

class CurrencySelection : AppCompatActivity() {
    private lateinit var currencyList : RecyclerView
    private val currencyAdapter = CurrencyAdapter(DataController.liveData.value)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_selection)

        currencyAdapter.setIntent(intent)

        val layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, layoutManager.orientation)

        currencyList = findViewById(R.id.currencyList)
        currencyList.layoutManager = layoutManager
        currencyList.addItemDecoration(decoration)
        currencyList.adapter = currencyAdapter
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