# Kotlin.someExtensions
Few extensions functions for Kotlin and Android

# ImageExtensions.kt 
 ```kotlin 
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
 ```
# ContextExtensions.kt 
 ```kotlin 
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
    val resourceId = this.resources.getIdentifier("status_bar_height", "dimen",
            "android")
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
fun Context.dpToPx(dp : Int): Float {
    val displayMetrics = this.resources.displayMetrics
    return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).toFloat()
} 
 
/**
 * Get color from resources
 */
fun Context.getCompatColor(@ColorRes colorInt: Int) : Int =
        ContextCompat.getColor(this, colorInt) 
 
/**
 * Get drawable from resources
 */
fun Context.getCompatDrawable(@DrawableRes drawableRes: Int) : Drawable =
        ContextCompat.getDrawable(this, drawableRes) 
 ```
# ActivityExtensions.kt 
 ```kotlin 
 /**
 * AppCompatActivity's toolbar visibility modifiers
 */

fun AppCompatActivity.hideToolbar() {
    supportActionBar?.hide()
} 
 
/**
 * Returns display density as ...DPI
 */
fun AppCompatActivity.getDisplayDensity(): String {
    val metrics = DisplayMetrics()
    this.windowManager.defaultDisplay.getMetrics(metrics)
    return when (metrics.densityDpi) {
        DisplayMetrics.DENSITY_LOW -> "LDPI"
        DisplayMetrics.DENSITY_MEDIUM -> "MDPI"
        DisplayMetrics.DENSITY_HIGH -> "HDPI"
        DisplayMetrics.DENSITY_XHIGH -> "XHDPI"
        DisplayMetrics.DENSITY_XXHIGH -> "XXHDPI"
        DisplayMetrics.DENSITY_XXXHIGH -> "XXXHDPI"
        else -> "XXHDPI"
    }
} 
 
/**
 * Sets color to toolbar in AppCompatActivity
 */
fun AppCompatActivity.setToolbarColor(@ColorRes color: Int) {
    this.supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this,
            color)))
} 
 
/**
 * Perform replace for a support fragment
 */
inline fun AppCompatActivity.transact(action: FragmentTransaction.() -> Unit) {
    supportFragmentManager.beginTransaction().apply {
        action()
    }.commit()
} 
 ```
# ThreadingBlocks.kt 
 ```kotlin 
 /**
 * Executes block of code on Android's main thread. Can be called from background thread.
 */
inline fun uiThreadExecutor(crossinline block: () -> Unit) {
    val mainHandler = Handler(Looper.getMainLooper())
    mainHandler.post{
        block()
    }
} 
 
/**
 * Executes a function using RxJava observable on a separate thread and
 * exposes it's response as lambda on main thread
 * REQUIRED: RxJava, RxKotlin, RxAndroid
 */
fun <T> asyncRxExecutor(heavyFunction: () -> T, response : (response : T?) -> Unit) {
    val observable = Single.create<T>({e ->
        e.onSuccess(heavyFunction())
    })
    observable.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t: T? ->
                response(t)
            }
} 
 
/**
 * Executes a function using Kotlin coroutines on a separate thread pool and
 * exposes it's response as lambda on main thread.
 * Thread pool is maintained by Anko Coroutines lib
 * REQUIRED: Anko Coroutines
 */
fun <T> asyncCoroutinesExecutor(heavyFunction: () -> T, response : (response : T?) -> Unit) {
    async(UI) {
        val data : Deferred<T> = bg {
            heavyFunction()
        }
        response(data.await())
    }
} 
 ```
# GeneralExtensions.kt 
 ```kotlin 
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
 ```
# ViewExtensions.kt 
 ```kotlin 
 /**
 * Visibility modifiers and check functions
 */

fun View.isVisibile(): Boolean = this.visibility == View.VISIBLE 
 
/**
 * Sets text and content description using same string
 */
fun TextView.setTextWithContentDescription(value : String?) {
    text = value
    contentDescription = value
} 
 
/**
 * Button enabling/disabling modifiers
 */

fun Button.disableButton() {
    isEnabled = false
    alpha = 0.3f
} 
 
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
 ```
# StringExtensions.kt 
 ```kotlin 
 /**
 * Converts string to integer safely otherwise returns zero
 */
fun String.toIntOrZero() : Int {
    var value = 0
    justTry {
        value = this.toInt()
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
 ```
# MetricExtensions.kt 
 ```kotlin 
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
 ```
# Drawable.kt 
 ```kotlin 
 /**
 * Returns a compat drawable with tint added
 */
fun Drawable.withTint(colorInt: Int): Drawable {
    return with(this) {
        DrawableCompat.wrap(this).apply {
            DrawableCompat.setTint(this, colorInt)
        }
    }
} 
 ```
# DateExtensions.kt 
 ```kotlin 
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
 ```