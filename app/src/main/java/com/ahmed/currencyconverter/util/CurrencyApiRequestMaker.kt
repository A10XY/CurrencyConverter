package com.ahmed.currencyconverter.util

import okhttp3.HttpUrl
import okhttp3.Request

object CurrencyApiRequestMaker {
    fun makeCurrencyConvertRequest(fromCurrency: String, toCurrency: String, amount: String): Request {
        return Request.Builder().apply {
            url(createCovertCurrencyRequestUrl(fromCurrency, toCurrency, amount))
        }.build()
    }

    private fun createBaseUrl(): HttpUrl.Builder {
        return HttpUrl.Builder().apply {
            scheme(Constants.SCHEME)
            host(Constants.HOST)
        }
    }

    private fun createCovertCurrencyRequestUrl(
        fromCurrency: String,
        toCurrency: String,
        amount: String
    ): HttpUrl {
        return createBaseUrl().apply {
            addPathSegment(Constants.VERSION_PATH_SEGMENT)
            addPathSegment(Constants.API_KEY_PATH_SEGMENT)
            addPathSegment(Constants.PAIR_PATH_SEGMENT)
            addPathSegment(fromCurrency)
            addPathSegment(toCurrency)
            addPathSegment(amount)
        }.build()
    }
}