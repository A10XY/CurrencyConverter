package com.ahmed.currencyconverter.ui.home

import com.ahmed.currencyconverter.data.model.CurrencyConvertResponse

interface HomeView {
    fun onCurrencyApiRequestInProgress()
    fun onCurrencyApiResponseFail(message: String)
    fun onCurrencyApiResponseSuccess(currencyConvertResponse: CurrencyConvertResponse)
    fun onAmountIsEmpty()
}