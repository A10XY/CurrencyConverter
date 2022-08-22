package com.ahmed.currencyconverter.presenter

import android.content.Context
import com.ahmed.currencyconverter.data.model.ChooseCurrencyDialogModel
import com.ahmed.currencyconverter.ui.dialog.DialogView

class DialogPresenter(private val dialogView: DialogView) {
    private val dialogModel = ChooseCurrencyDialogModel()
    fun getCurrenciesList(context: Context, excludedCurrencyCodes: List<String>) {
        val currenciesList = dialogModel.getAllCurrencies(context)
        if (excludedCurrencyCodes.isNotEmpty()) {
            val newList = currenciesList.filter { it.currencyCode != excludedCurrencyCodes[0] && it.currencyCode != excludedCurrencyCodes[1] }
            dialogView.onCurrenciesListProvided(newList)
        } else dialogView.onCurrenciesListProvided(currenciesList)
    }
}