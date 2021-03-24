package com.example.currencyconverter.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.w3c.dom.Document
import org.w3c.dom.NodeList
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.math.round


object Converter : ViewModel() {
    private val url = URL("https://www.cbr.ru/scripts/XML_daily.asp")
    private lateinit var doc: Document
    private lateinit var valutes: NodeList

    private val data: ArrayList<ArrayList<String>> = ArrayList()
    private val liveData = MutableLiveData<ArrayList<ArrayList<String>>>()

    fun getData(): LiveData<ArrayList<ArrayList<String>>> {
        return liveData
    }

    fun connect() {
        val builderFactory = DocumentBuilderFactory.newInstance()
        val documentBuilder = builderFactory.newDocumentBuilder()
        doc = documentBuilder.parse(url.openConnection().getInputStream())
        @Suppress("SpellCheckingInspection")
        valutes = doc.getElementsByTagName("Valute")

        for (i in 0 until valutes.length) {
            val currency = valutes.item(i)

            data.add(ArrayList())

            for (j in 0 until currency.childNodes.length) {
                data[i].add(currency.childNodes.item(j).textContent.replace(",", "."))
            }
        }

        data.sortBy { it[3] }

        liveData.postValue(data)
    }

    fun convert(value: Double, indexCurrency1: Int, indexCurrency2: Int): Double {
        var result = 0.0

        if (value != 0.0) {
            val resultInRubles = data[indexCurrency1][4].toDouble() / data[indexCurrency1][2].toDouble() * value

            result = resultInRubles / data[indexCurrency2][4].toDouble() * data[indexCurrency2][2].toDouble()
        }

        return round(result * 100) / 100
    }
}