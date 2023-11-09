package com.mayv.ctgate.network

import com.mayv.ctgate.model.GateLog
import com.mayv.ctgate.model.Soldier
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface SMISApi {

    @GET("gate/get-soldier")
    suspend fun getSoldier(@Query("national_id") nationalId: Long): Soldier


    @GET("gate/get-all")
    suspend fun getAllGateLogs(@Query("page") page: Int, @Query("size") size: Int): List<GateLog>


    @GET("gate/soldier-logs")
    suspend fun getSoldierLogs(@Query("national_id") nationalId: Long): List<GateLog>

    @GET("image/get")
    suspend fun getSoldierImage(@Query("national_id") nationalId: Long): ResponseBody

    @GET(".")
    suspend fun checkConnection(): Response<Soldier>
}