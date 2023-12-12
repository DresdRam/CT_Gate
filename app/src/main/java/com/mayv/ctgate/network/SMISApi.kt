package com.mayv.ctgate.network

import com.mayv.ctgate.model.Authorization
import com.mayv.ctgate.model.GateLog
import com.mayv.ctgate.model.Login
import com.mayv.ctgate.model.Soldier
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface SMISApi {

    @GET("gate/get-soldier")
    suspend fun getSoldier(
        @Query("national_id") nationalId: Long,
        @Header("Authorization") authToken: String
    ): Response<Soldier>

    @GET("gate/get-all")
    suspend fun getAllGateLogs(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Header("Authorization") authToken: String
    ): Response<List<GateLog>>

    @GET("gate/soldier-logs")
    suspend fun getSoldierLogs(
        @Query("national_id") nationalId: Long,
        @Header("Authorization") authToken: String
    ): Response<List<GateLog>>

    @GET("image/get")
    suspend fun getSoldierImage(
        @Query("national_id") nationalId: Long,
        @Header("Authorization") authToken: String
    ): Response<ResponseBody>

    @GET("soldier/search")
    suspend fun searchForSoldiers(
        @Query("name") name: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 20,
        @Query("excludeRemoved") excludeRemoved: Int,
        @Header("Authorization") authToken: String
    ): Response<List<Soldier>>

    @POST("user/login")
    suspend fun login(@Body login: Login): Response<Authorization>

}