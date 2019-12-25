package `in`.jitinsharma.kotlin.some.extensions.context

import android.Manifest
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat
import java.io.IOException
import java.nio.charset.Charset

/**
 * Checks network connectivity
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isNetworkStatusAvailable(): Boolean {
    val connectivityManager = this
        .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val netInfo = it.activeNetworkInfo
        netInfo?.let {
            if (netInfo.isConnected) return true
        }
    }
    return false
}

/**
 * Execute block of code if network is available
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.withNetwork(block: () -> Unit) {
    if (isNetworkStatusAvailable()) {
        block()
    }
}

/**
 * Loads content of file from assets as String using UTF-8 charset
 */
fun Context.loadFromAsset(jsonName: String): String? {
    var stream: String? = null
    try {
        val inputStream = this.assets.open(jsonName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        stream = String(buffer, Charset.forName("UTF-8"))
    } catch (e: IOException) {
    }
    return stream
}

/**
 * Computes status bar height
 */
fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = this.resources.getIdentifier(
        "status_bar_height", "dimen",
        "android"
    )
    if (resourceId > 0) {
        result = this.resources.getDimensionPixelSize(resourceId)
    }
    return result
}


/**
 * Computes screen height
 */
fun Context.getScreenHeight(): Int {
    var screenHeight = 0
    val wm = this.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
    wm?.let {
        val metrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(metrics)
        screenHeight = metrics.heightPixels
    }
    return screenHeight
}

/**
 * Convert dp integer to pixel
 */
fun Context.toPx(dp: Int): Float {
    val displayMetrics = this.resources.displayMetrics
    return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
}

/**
 * Get color from resources
 */
fun Context.getCompatColor(@ColorRes colorInt: Int): Int =
    ContextCompat.getColor(this, colorInt)

/**
 * Get drawable from resources
 */
fun Context.getCompatDrawable(@DrawableRes drawableRes: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableRes)
