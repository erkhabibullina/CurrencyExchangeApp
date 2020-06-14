package com.erkhabibullina.currencyexchangeapp.domain.repository

import com.erkhabibullina.currencyexchangeapp.datasource.remote.CurrencyApi
import com.erkhabibullina.currencyexchangeapp.domain.entities.CurrencyRateData
import io.reactivex.Single

class CurrencyRepository(private val currencyApi: CurrencyApi)  {

    fun getRates(base: String): Single<CurrencyRateData> {
        return currencyApi.getCurrencyRateAsync(base)
    }
}