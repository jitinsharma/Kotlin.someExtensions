package com.amadeus.mercimdp.utils

import java.util.*

/**
 * Wrapping try/catch to ignore catch block
 */
inline fun <T> justTry(block: () -> T) = try { block() } catch (e: Throwable) {}

/**
 * Converts string to integer safely otherwise returns zero
 */
fun String.toIntOrZero() : Int {
    var value = 0
    justTry {
        value = toInt()
    }
    return value
}

/**
 * Converts a string to boolean such as 'Y', 'yes', 'TRUE'
 */

fun String.toBoolean(): Boolean {
    return this != "" &&
            (this.equals("TRUE", ignoreCase = true)
            || this.equals("Y", ignoreCase = true)
            || this.equals("YES", ignoreCase = true))
}

/**
 * Converts string to camel case. Handles multiple strings and empty strings
 */
fun String.convertToCamelCase(): String {
    var titleText = ""
    if (!this.isEmpty()) {
        val words = this.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        words.filterNot { it.isEmpty() }
                .map { it.substring(0, 1).toUpperCase() + it.substring(1).toLowerCase() }
                .forEach { titleText += it + " " }
    }
    return titleText.trim { it <= ' ' }
}

/**
 * Convert a given date to milliseconds
 */
fun Date.toMillis() : Long {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.timeInMillis
}