package `in`.jitinsharma.kotlin.some.extensions.views

import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * Sets color to status bar
 */
fun Window.addStatusBarColor(@ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        this.statusBarColor = ContextCompat.getColor(this.context, color)
    }
}