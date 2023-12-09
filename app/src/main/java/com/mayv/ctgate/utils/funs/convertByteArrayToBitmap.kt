package com.mayv.ctgate.utils.funs

import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun convertByteArrayToBitmap(imageData: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
}