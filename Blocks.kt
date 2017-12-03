package io.github.jitinsharma.kotlinsomeextensions

import android.os.Build

/**
 * Wrapping try/catch to ignore catch block
 */
inline fun <T> justTry(block: () -> T) = try { block() } catch (e: Throwable) {}

/**
 * App's debug mode
 */
inline fun debugMode(body : () -> Unit) {
    if (BuildConfig.DEBUG) {
        body()
    }
}

/**
 * For functionality supported above API 21 (Eg. Material design stuff)
 */
private inline fun lollipopAndAbove(body : () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        body()
    }
}