package com.mayv.ctgate.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.mayv.ctgate.data.DataOrException
import com.mayv.ctgate.model.Soldier
import com.mayv.ctgate.network.SMISApi
import com.mayv.ctgate.utils.byteArrayToBitmap
import okhttp3.ResponseBody
import javax.inject.Inject

class SoldierRepository @Inject constructor(private val api: SMISApi) {

    private val soldierDataOrException = DataOrException<Soldier, Boolean, Exception>()

    private val imageDataOrException = DataOrException<Bitmap, Boolean, Exception>()

    suspend fun getSoldierData(nationalId: Long): DataOrException<Soldier, Boolean, Exception> {

        try {
            soldierDataOrException.loading = true
            soldierDataOrException.data = api.getSoldier(nationalId)
            if(soldierDataOrException.data.toString().isNotEmpty()) soldierDataOrException.loading = false
        }catch (exception: Exception){
            soldierDataOrException.exception = exception
        }

        return soldierDataOrException
    }

    suspend fun getSoldierImage(nationalId: Long): DataOrException<Bitmap, Boolean, Exception> {

        try {
            imageDataOrException.loading = true
            val byteArray = api.getSoldierImage(nationalId).byteStream().readBytes()
            imageDataOrException.data = byteArrayToBitmap(byteArray)
            if(imageDataOrException.data.toString().isNotEmpty()) imageDataOrException.loading = false
        }catch (exception: Exception){
            imageDataOrException.exception = exception
        }

        return imageDataOrException
    }

}