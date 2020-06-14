package com.erkhabibullina.currencyexchangeapp.commons.extension

import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.request.CachePolicy
import com.erkhabibullina.currencyexchangeapp.R
import com.erkhabibullina.currencyexchangeapp.commons.util.CurrencyUtil.Companion.getFlagImageResId

@BindingAdapter(value = ["flagImg"])
fun flgImg(view: ImageView, country : String?) {

    if(!TextUtils.isEmpty(country)) {

        val countryCode = getFlagImageResId(view.context,country)

        view.load(countryCode) {
            crossfade(true)
            placeholder(R.drawable.ic_flag_placeholder)
            error(R.drawable.ic_flag_placeholder)
            memoryCachePolicy(CachePolicy.DISABLED)
        }
    }
}

fun EditText.moveCursorToProperPosition() {
    this.setSelection(this.text.length)
}