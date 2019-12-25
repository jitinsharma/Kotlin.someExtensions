package `in`.jitinsharma.kotlin.some.extensions.core

import java.util.*

/**
 * Converts string to integer safely otherwise returns zero
 */
fun String.toIntOrZero(): Int {
    var value = 0
    try {
        value = this.toInt()
    } catch (_: Exception) {
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
    var text = ""
    if (this.isNotEmpty()) {
        val words = this.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        words.filterNot { it.isEmpty() }
            .map {
                it.substring(
                    0,
                    1
                ).toUpperCase(Locale.getDefault()) + it.substring(1).toLowerCase(Locale.getDefault())
            }
            .forEach { text += "$it " }
    }
    return text.trim { it <= ' ' }
}
