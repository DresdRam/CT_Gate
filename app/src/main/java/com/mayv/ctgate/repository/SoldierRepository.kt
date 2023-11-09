package com.mayv.ctgate.repository

import android.graphics.Bitmap
import android.util.Log
import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.Soldier
import com.mayv.ctgate.network.SMISApi
import com.mayv.ctgate.utils.byteArrayToBitmap
import javax.inject.Inject

class SoldierRepository @Inject constructor(private val api: SMISApi) {

    private val soldierResource = Resource<Soldier>()

    private val imageResource = Resource<Bitmap>()

    suspend fun getSoldierData(nationalId: Long): Resource<Soldier> {
        try {
            soldierResource.loading = true
            soldierResource.data = api.getSoldier(nationalId)
            soldierResource.loading = false
        }catch (exception: Exception){
            soldierResource.loading = false
            soldierResource.failed = true
            soldierResource.exception = exception
        }

        return soldierResource
    }

    suspend fun getSoldierImage(nationalId: Long): Resource<Bitmap> {

        try {
            imageResource.loading = true
            val byteArray = api.getSoldierImage(nationalId).byteStream().readBytes()
            imageResource.data = byteArrayToBitmap(byteArray)
            imageResource.loading = false
        }catch (exception: Exception){
            imageResource.loading = false
            imageResource.failed = true
            imageResource.exception = exception
        }

        return imageResource
    }

    suspend fun checkConnection(): Boolean {
        return try {
            val res = api.checkConnection()
            res.code() == 404 || res.code() == 500
        }catch (exception: Exception){
            Log.e("Connection", "checkConnection: ${exception.message}")
            false
        }
    }

}