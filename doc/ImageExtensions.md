
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