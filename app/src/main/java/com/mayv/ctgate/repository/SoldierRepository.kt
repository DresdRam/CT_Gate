package com.mayv.ctgate.repository

import android.graphics.Bitmap
import android.util.Log
import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.Soldier
import com.mayv.ctgate.network.SMISApi
import com.mayv.ctgate.utils.funs.convertByteArrayToBitmap
import com.mayv.ctgate.utils.funs.getExceptionFromStatusCode
import javax.inject.Inject

class SoldierRepository @Inject constructor(private val api: SMISApi) {

    suspend fun getSoldierData(nationalId: Long, token: String): Resource<Soldier> {

        val soldierResource = Resource<Soldier>()

        try {

            val response = api.getSoldier(nationalId, token)
            soldierResource.statusCode = response.code()

            if (response.isSuccessful) {
                soldierResource.data = response.body()
            } else {
                soldierResource.exception = getExceptionFromStatusCode(response.code())
                Log.e("TAG", "getSoldierData: ${soldierResource.exception.message}")
            }
        } catch (exception: Exception) {
            soldierResource.exception = getExceptionFromStatusCode(400)
            soldierResource.statusCode = 400
            Log.e("TAG", "getSoldierData: ${exception.message}")
        }

        return soldierResource
    }

    suspend fun getSoldierImage(nationalId: Long, token: String): Resource<Bitmap> {

        val imageResource = Resource<Bitmap>()

        try {

            val response = api.getSoldierImage(nationalId, token)
            imageResource.statusCode = response.code()

            if (response.isSuccessful) {
                imageResource.data = response.body()?.byteStream()
                    ?.let { convertByteArrayToBitmap(it.readBytes()) }
            } else {
                imageResource.exception = getExceptionFromStatusCode(response.code())
            }
        } catch (exception: Exception) {
            imageResource.exception = getExceptionFromStatusCode(400)
            imageResource.statusCode = 400
        }

        return imageResource
    }

}