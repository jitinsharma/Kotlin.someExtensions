
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