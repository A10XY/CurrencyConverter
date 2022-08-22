package com.ahmed.currencyconverter.data.model

import com.ahmed.currencyconverter.util.Constants
import com.google.gson.annotations.SerializedName

data class CurrencyConvertResponse (
    @SerializedName(Constants.CONVERSION_RATE)
    val conversionRate: String,
    @SerializedName(Constants.CONVERSION_RESULT)
    val conversionResult: String
    )