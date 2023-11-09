package com.mayv.ctgate.network

import com.mayv.ctgate.model.Soldier
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface SMISApi {

    @GET("soldier/gate-soldier")
    suspend fun getSoldier(@Query("national_id") nationalId: Long): Soldier

     // CODE_REVIEW: since you developed the api yourself, it's better to cache these images on server side and just return the url.
    @GET("image/get")
    suspend fun getSoldierImage(@Query("national_id") nationalId: Long): ResponseBody

}
