package com.ahmed.currencyconverter.data.model

import android.content.Context
import com.ahmed.currencyconverter.util.getJsonFromAssets
import com.google.gson.Gson

class ChooseCurrencyDialogModel {
    private val gson = Gson()
    fun getAllCurrencies(context: Context): List<CurrencyInfo> {
        return gson.fromJson(getJsonFromAssets(context, "currency_codes.json"), CurrencyInfoList::class.java).currencyInfoList
    }
}