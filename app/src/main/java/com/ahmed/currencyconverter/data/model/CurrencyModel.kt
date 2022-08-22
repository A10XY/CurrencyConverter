package com.ahmed.currencyconverter.data.model

import com.ahmed.currencyconverter.data.ApiRequestState
import com.ahmed.currencyconverter.data.services.BaseService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CurrencyModel(private val currencyApi: BaseService) {
    fun convertCurrencyUsingApi(fromCurrency: String, toCurrency: String, amount: String): Flow<ApiRequestState<CurrencyConvertResponse>> {
        return flow {
            emit(ApiRequestState.Loading)
            emit(currencyApi.convertCurrency(fromCurrency, toCurrency, amount))
        }.flowOn(Dispatchers.Default)
    }
}