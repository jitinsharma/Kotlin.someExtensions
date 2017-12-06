package io.github.jitinsharma.kotlinsomeextensions

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build

/**
 * Wrapping try/catch to ignore catch block
 */
inline fun <T> justTry(block: () -> T) = try { block() } catch (e: Throwable) {}

/**
 * App's debug mode
 */
inline fun debugMode(block : () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}

/**
 * For functionality supported above API 21 (Eg. Material design stuff)
 */
inline fun lollipopAndAbove(block : () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        block()
    }
}

/**
 * Execute block of code if network is available
 */
@SuppressLint("MissingPermission")
fun Context.withConnection(block: () -> Unit) {
    val connectivityManager = this
            .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val netInfo = it.activeNetworkInfo
        netInfo?.let {
            if (netInfo.isConnected) {
                block()
            }
        }
    }
}