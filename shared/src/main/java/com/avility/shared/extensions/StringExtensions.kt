package com.avility.shared.extensions

import timber.log.Timber
import java.text.NumberFormat
import java.util.Locale

fun String.toFormatCurrency(): String {
    try {
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

        return if (this.length > 2) {
            val lastTwoDigits = this.takeLast(2)
            val remainValue = this.dropLast(2)

            val correctFormat = "$remainValue.$lastTwoDigits"
            val number = correctFormat.toFloat()

            currencyFormat.format(number)
        } else {
            val number = this.toInt()
            currencyFormat.format(number)
        }

    } catch (e: Exception) {
        Timber.e(e)
    }

    return this
}