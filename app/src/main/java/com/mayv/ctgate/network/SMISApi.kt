package com.mayv.ctgate.network

import com.mayv.ctgate.model.Soldier
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface SMISApi {

    @GET("soldier/get-soldier")
    fun getSoldier(@Query("national_id") nationalId: Long): Soldier

    @GET("image/get")
    fun getSoldierImage(@Query("national_id") nationalId: Long): ResponseBody

}