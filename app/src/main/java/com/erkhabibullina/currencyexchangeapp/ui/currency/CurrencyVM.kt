package com.erkhabibullina.currencyexchangeapp.ui.currency

import androidx.lifecycle.MutableLiveData
import com.erkhabibullina.currencyexchangeapp.commons.base.BaseViewModel
import com.erkhabibullina.currencyexchangeapp.datasource.remote.NetworkState
import com.erkhabibullina.currencyexchangeapp.domain.entities.RateList
import com.erkhabibullina.currencyexchangeapp.domain.usecase.CurrencyUseCase

class CurrencyVM(private val currencyUseCase: CurrencyUseCase) : BaseViewModel() {

    // OBSERVABLES ---
    var networkState : MutableLiveData<NetworkState<ArrayList<RateList>>> = MutableLiveData()

    fun handleCurrencyRate(currency : String, amount: Double,isAmountUpdated : Boolean) {
        addToDisposable(currencyUseCase.checkRates(currency,amount,isAmountUpdated))
        networkState = currencyUseCase.getNetworkState()
    }
}