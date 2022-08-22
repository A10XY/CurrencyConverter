package com.ahmed.currencyconverter.ui.dialog

import com.ahmed.currencyconverter.data.model.CurrencyInfo

interface CurrencyDialogClicksListener {
    fun onCurrencyItemClickListener(currencyInfo: CurrencyInfo)
}