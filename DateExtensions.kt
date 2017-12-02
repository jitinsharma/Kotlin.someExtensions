package io.github.jitinsharma.kotlinsomeextensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Convert a given date to milliseconds
 */
fun Date.toMillis() : Long {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.timeInMillis
}

/**
 * Checks if dates are same
 */
fun Date.isSame(to : Date) : Boolean {
    val sdf = SimpleDateFormat("yyyMMdd", Locale.getDefault())
    return sdf.format(this) == sdf.format(to)
}

/**
 * Converts raw string to date object using [SimpleDateFormat]
 */
fun String.convertStringToDate(simpleDateFormatPattern: String): Date? {
    val simpleDateFormat = SimpleDateFormat(simpleDateFormatPattern, Locale.getDefault())
    var value: Date? = null
    justTry {
        value = simpleDateFormat.parse(this)
    }
    return value
}