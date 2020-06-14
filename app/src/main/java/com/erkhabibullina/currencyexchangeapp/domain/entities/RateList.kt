package com.erkhabibullina.currencyexchangeapp.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RateList(val currency: String, val rate: Double) : Parcelable
