package com.ahmed.currencyconverter.presenter

import android.util.Log
import com.ahmed.currencyconverter.data.ApiRequestState
import com.ahmed.currencyconverter.data.model.CurrencyModel
import com.ahmed.currencyconverter.data.services.CurrencyApiService
import com.ahmed.currencyconverter.ui.home.HomeView
import com.ahmed.currencyconverter.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class HomePresenter(private val view: HomeView, private val lifeCycleScope: CoroutineScope) {
    private var baseService = CurrencyApiService()
    private val currencyModel = CurrencyModel(baseService)

    fun convertCurrencyUsingApi(fromCurrency: String, toCurrency: String, amount: String) {
        if (amount.isNotEmpty()) {
            val flow = currencyModel.convertCurrencyUsingApi(fromCurrency, toCurrency, amount)
            lifeCycleScope.launch {
                flow.onCompletion {
                    Log.d(Constants.MAIN_ACTIVITY_LOG_TAG, "Flow Collection Ended!")
                }.catch {
                    Log.d(Constants.MAIN_ACTIVITY_LOG_TAG, "Flow Collection Error!")
                }.collect {
                    when (it) {
                        is ApiRequestState.Loading -> view.onCurrencyApiRequestInProgress()
                        is ApiRequestState.Fail -> view.onCurrencyApiResponseFail(it.message)
                        is ApiRequestState.Success -> view.onCurrencyApiResponseSuccess(it.data)
                    }
                }
            }
        } else view.onAmountIsEmpty()
    }
}