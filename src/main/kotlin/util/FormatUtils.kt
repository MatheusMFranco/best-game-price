package org.magalzim.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.*

fun Long.transformToDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val date = Date(this * 1000)
    val localDate: LocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val days = Period.between(localDate, LocalDate.now()).days
    return formatter.format(date) + " ($days days ago)"
}

fun Double.transformToDecimal(): Double {
    val decimalFormat = DecimalFormat("#.00", DecimalFormatSymbols(Locale.US))
    return decimalFormat.format(this).toDouble()
}
