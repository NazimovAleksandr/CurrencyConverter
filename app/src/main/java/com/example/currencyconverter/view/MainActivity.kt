package com.example.currencyconverter.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.R
import com.example.currencyconverter.controller.DataController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork

        if (network != null) {
            DataController.getData()

            DataController.liveData.observe(this) {
                getConverter()
            }
        } else {
            findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE

            Toast.makeText(this, "Ошибка подключения", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getConverter() {
        val intent = Intent(this, CurrencyConverter::class.java)

        startActivity(intent)
    }
}