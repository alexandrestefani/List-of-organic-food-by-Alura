package com.alexandrestefani.org.Extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun Double.formatToBrazilianCoin(): String {
    val formatador: NumberFormat = NumberFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return formatador.format(this)
}