package com.ahmed.currencyconverter.data.services

import com.ahmed.currencyconverter.data.ApiRequestState
import com.ahmed.currencyconverter.data.model.CurrencyConvertResponse

interface BaseService {
    fun convertCurrency(fromCurrency: String, toCurrency: String, amount: String): ApiRequestState<CurrencyConvertResponse>
}