package `in`.jitinsharma.kotlin.some.extensions.core


/**
 * Convert Celsius temperature to Fahrenheit
 */
fun Double.toFahrenheit(): Double = (this * 1.8) + 32

/**
 * Convert Fahrenheit temperature to Celsius
 */
fun Double.toCelsius(): Double = (this - 32) * 5 / 9

/**
 * Convert meters to miles
 */
fun Double.toMiles(): Double {
    return if (this != 0.0) {
        this / 1609.34
    } else -1.0
}
