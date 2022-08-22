package com.ahmed.currencyconverter.data.model

import com.ahmed.currencyconverter.util.Constants
import com.google.gson.annotations.SerializedName

data class CurrencyInfo(
    @SerializedName(Constants.CURRENCY_NAME)
    val currencyName: String,
    @SerializedName(Constants.CURRENCY_CODE)
    val currencyCode: String
)
