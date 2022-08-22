package com.ahmed.currencyconverter.ui.dialog

import com.ahmed.currencyconverter.data.model.CurrencyInfo

interface CurrencyChooserContract {
    fun onFromCurrencyChosen(currencyInfo: CurrencyInfo)
    fun onToCurrencyChosen(currencyInfo: CurrencyInfo)
}