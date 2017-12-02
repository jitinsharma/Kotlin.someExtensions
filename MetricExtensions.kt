package io.github.jitinsharma.kotlinsomeextensions


/**
 * Convert Celsius temperature to Fahrenheit
 */
fun Double.celsiusToFahrenheit() : Double = (this * 1.8) + 32

/**
 * Convert Fahrenheit temperature to Celsius
 */
fun Double.fahrenheitToCelsius() : Double = (this - 32) * 5/9

/**
 * Convert meters to miles
 */
fun Double.convertMetersToMiles(): Double {
    return if (this != 0.0) {
        this / 1609.34
    } else -1.0
}