package com.example.currencyconverter.view

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.*
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.R
import com.example.currencyconverter.controller.DataController


class CurrencyConverter : AppCompatActivity() {
    private lateinit var charCode1: TextView
    private lateinit var charCode2: TextView
    private var selectedCurrencyIndex1: Int = 10
    private var selectedCurrencyIndex2: Int = 11

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_converter)

        charCode1 = findViewById(R.id.textViewCharCode1)
        charCode2 = findViewById(R.id.textViewCharCode2)

        charCode1.text = DataController.getCharCode(selectedCurrencyIndex1)
        charCode2.text = DataController.getCharCode(selectedCurrencyIndex2)

        val currencySelection1 = findViewById<TextView>(R.id.textViewCurrencySelection1)
        val currencySelection2 = findViewById<TextView>(R.id.textViewCurrencySelection2)

        currencySelection1.setOnClickListener { showCurrency(1) }
        currencySelection2.setOnClickListener { showCurrency(2) }

        val editTextCurrency1 = findViewById<EditText>(R.id.editTextCurrency1)
        val editTextCurrency2 = findViewById<EditText>(R.id.editTextCurrency2)

        editTextCurrency1.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                editTextCurrency2.setText(
                    DataController.convert(
                        editTextCurrency1.text.toString(),
                        selectedCurrencyIndex1,
                        selectedCurrencyIndex2
                    )
                )
                return@OnEditorActionListener true
            }
            false
        })

        editTextCurrency2.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                editTextCurrency1.setText(
                    DataController.convert(
                        editTextCurrency2.text.toString(),
                        selectedCurrencyIndex2,
                        selectedCurrencyIndex1
                    )
                )
                return@OnEditorActionListener true
            }
            false
        })

        findViewById<ImageView>(R.id.imageViewLeftArrow).setOnClickListener {
            editTextCurrency1.setText(
                DataController.convert(
                    editTextCurrency2.text.toString(),
                    selectedCurrencyIndex2,
                    selectedCurrencyIndex1
                )
            )
        }

        findViewById<ImageView>(R.id.imageViewRightArrow).setOnClickListener {
            editTextCurrency2.setText(
                DataController.convert(
                    editTextCurrency1.text.toString(),
                    selectedCurrencyIndex1,
                    selectedCurrencyIndex2
                )
            )
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                selectedCurrencyIndex1 = data?.getStringExtra("index").toString().toInt()
                charCode1.text = DataController.getCharCode(selectedCurrencyIndex1)
            } else {
                selectedCurrencyIndex2 = data?.getStringExtra("index").toString().toInt()
                charCode2.text = DataController.getCharCode(selectedCurrencyIndex2)
            }
        }
    }

    private fun showCurrency(number: Int) {
        val intent = Intent(this, CurrencySelection::class.java)

        intent.putExtra("selected1", selectedCurrencyIndex1)
        intent.putExtra("selected2", selectedCurrencyIndex2)

        startActivityForResult(intent, number)
    }
}