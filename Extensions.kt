package com.amadeus.mercimdp.utils

import java.text.SimpleDateFormat
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

/**
 * Convert byte array to bitmap
 */
fun ByteArray.convertBytesToBitmap(): Bitmap =
        BitmapFactory.decodeByteArray(this, 0, size)

/**
 * Convert bitmap to a byte array
 */
fun Bitmap.convertBitmapToBytes(): ByteArray {
    val bytes: ByteArray
    val stream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 0, stream)
    bytes = stream.toByteArray()
    return bytes
}

/**
 * Convert Celsius temperature to Fahrenheit
 */
fun Double.celsiusToFahrenheit() : Double = (this * 1.8) + 32

/**
 * Convert Fahrenheit temperature to Celsius
 */
fun Double.fahrenheitToCelsius() : Double = (this - 32) * 5/9