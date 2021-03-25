package com.example.currencyconverter.view

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.currencyconverter.R
import com.example.currencyconverter.controller.DataController
import com.example.currencyconverter.databinding.ActivityCurrencyConverterBinding

class CurrencyConverter : AppCompatActivity() {
    private var selectedCurrencyIndex1: Int = 10
    private var selectedCurrencyIndex2: Int = 11

    private lateinit var binding: ActivityCurrencyConverterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_currency_converter)

        binding.textViewCharCode1.text = DataController.getCharCode(selectedCurrencyIndex1)
        binding.textViewCharCode2.text = DataController.getCharCode(selectedCurrencyIndex2)

        binding.textViewCurrencySelection1.setOnClickListener { showCurrency(1) }
        binding.textViewCurrencySelection2.setOnClickListener { showCurrency(2) }

        binding.editTextCurrency1.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.editTextCurrency2.setText(
                    DataController.convert(
                        binding.editTextCurrency1.text.toString(),
                        selectedCurrencyIndex1,
                        selectedCurrencyIndex2
                    )
                )
                return@setOnEditorActionListener true
            }
            false
        }

        binding.editTextCurrency2.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.editTextCurrency1.setText(
                    DataController.convert(
                        binding.editTextCurrency2.text.toString(),
                        selectedCurrencyIndex2,
                        selectedCurrencyIndex1
                    )
                )
                return@setOnEditorActionListener true
            }
            false
        }

        binding.imageViewLeftArrow.setOnClickListener {
            binding.editTextCurrency1.setText(
                DataController.convert(
                    binding.editTextCurrency2.text.toString(),
                    selectedCurrencyIndex2,
                    selectedCurrencyIndex1
                )
            )
        }

        binding.imageViewRightArrow.setOnClickListener {
            binding.editTextCurrency2.setText(
                DataController.convert(
                    binding.editTextCurrency1.text.toString(),
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
                binding.textViewCharCode1.text = DataController.getCharCode(selectedCurrencyIndex1)
            } else {
                selectedCurrencyIndex2 = data?.getStringExtra("index").toString().toInt()
                binding.textViewCharCode2.text = DataController.getCharCode(selectedCurrencyIndex2)
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