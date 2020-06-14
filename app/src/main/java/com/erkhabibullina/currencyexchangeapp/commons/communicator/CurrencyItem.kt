package com.erkhabibullina.currencyexchangeapp.commons.communicator

interface CurrencyItem {
    fun onAmountChanged(currency: String, amount: Double)
}