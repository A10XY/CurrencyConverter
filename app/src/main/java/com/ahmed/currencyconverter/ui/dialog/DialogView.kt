package com.ahmed.currencyconverter.ui.dialog

import com.ahmed.currencyconverter.data.model.CurrencyInfo

interface DialogView {
    fun onCurrenciesListProvided(currenciesList: List<CurrencyInfo>)
}