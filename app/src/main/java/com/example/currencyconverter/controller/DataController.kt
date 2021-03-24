package com.example.currencyconverter.controller

import androidx.lifecycle.LiveData
import com.example.currencyconverter.model.Converter

object DataController {
    var liveData: LiveData<ArrayList<ArrayList<String>>> = Converter.getData()

    fun getData() {
        val thread = Thread {
            Converter.connect()
        }

        thread.start()
    }

    fun convert(value: String, currencyIndex1: Int, currencyIndex2: Int): String {
        if (value.isNotEmpty()) {
            return Converter.convert(value.toDouble(), currencyIndex1, currencyIndex2).toString()
        }

        return "0.0"
    }

    fun getCharCode(index: Int): String {
        return liveData.value?.get(index)?.get(1).toString()
    }
}

