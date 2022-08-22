package com.ahmed.currencyconverter.data.model

import com.ahmed.currencyconverter.util.Constants
import com.google.gson.annotations.SerializedName

data class CurrencyInfoList(
    @SerializedName(Constants.SUPPORTED_CURRENCIES)
    val currencyInfoList: List<CurrencyInfo>
)