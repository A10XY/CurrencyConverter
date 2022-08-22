package com.ahmed.currencyconverter.data.services

import android.util.Log
import com.ahmed.currencyconverter.data.ApiRequestState
import com.ahmed.currencyconverter.data.model.CurrencyConvertResponse
import com.ahmed.currencyconverter.util.Constants
import com.ahmed.currencyconverter.util.CurrencyApiRequestMaker
import com.google.gson.Gson
import okhttp3.OkHttpClient

class CurrencyApiService : BaseService {
    private val client = OkHttpClient()
    private val gson = Gson()

    override fun convertCurrency(
        fromCurrency: String,
        toCurrency: String,
        amount: String
    ): ApiRequestState<CurrencyConvertResponse> {
        Log.d(Constants.MAIN_ACTIVITY_LOG_TAG, "Starting Currency Api Service")
        Log.d(Constants.MAIN_ACTIVITY_LOG_TAG, "Converting $amount from $fromCurrency to $toCurrency ..")
        var state: ApiRequestState<CurrencyConvertResponse>
        try {
            val response = client.newCall(CurrencyApiRequestMaker.makeCurrencyConvertRequest(fromCurrency, toCurrency, amount)).execute()
            if (response.isSuccessful) {
                Log.d(Constants.MAIN_ACTIVITY_LOG_TAG, "Success")
                gson.fromJson(response.body?.string(), CurrencyConvertResponse::class.java).run {
                    state = ApiRequestState.Success(this)
                }
            } else {
                Log.d(Constants.MAIN_ACTIVITY_LOG_TAG, "Failed")
                state = ApiRequestState.Fail(response.message)
            }
        } catch (e: Exception) {
            state = ApiRequestState.Fail(e.message.toString())
        }
        return state
    }
}