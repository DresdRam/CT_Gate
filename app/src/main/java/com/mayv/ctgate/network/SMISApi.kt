package com.mayv.ctgate.network

import com.mayv.ctgate.model.Soldier
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface SMISApi {

    @GET("soldier")
    fun getSoldier(@Query("national_id") nationalId: Int): Soldier

}