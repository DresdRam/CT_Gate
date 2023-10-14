package com.mayv.ctgate.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun byteArrayToBitmap(imageData: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
}