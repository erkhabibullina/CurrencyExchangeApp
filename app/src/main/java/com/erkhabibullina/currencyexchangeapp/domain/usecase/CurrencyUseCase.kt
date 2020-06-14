package com.erkhabibullina.currencyexchangeapp.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.erkhabibullina.currencyexchangeapp.datasource.remote.NetworkState
import com.erkhabibullina.currencyexchangeapp.domain.entities.RateList
import com.erkhabibullina.currencyexchangeapp.domain.repository.CurrencyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CurrencyUseCase(private val currencyRepository: CurrencyRepository) {

    // FOR DATA ---
    private val networkState = MutableLiveData<NetworkState<ArrayList<RateList>>>()
    private val disposable = CompositeDisposable()

    fun checkRates(currency: String,amount: Double,isAmountUpdated : Boolean) : CompositeDisposable {

        if(isAmountUpdated) disposable.clear()

        disposable.add(currencyRepository.getRates(currency)
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .repeat()
            .subscribe({

                val rates = ArrayList<RateList>()
                rates.add(RateList(currency, amount))
                rates.addAll(it.rates.map { data -> RateList(data.key, data.value * amount) })

                networkState.postValue(NetworkState.Success(rates))

            }, {
                networkState.postValue(NetworkState.Error(it.message,null))
            }))

        return disposable
    }

    fun getNetworkState(): MutableLiveData<NetworkState<ArrayList<RateList>>> = networkState
}