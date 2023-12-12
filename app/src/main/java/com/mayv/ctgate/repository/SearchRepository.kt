package com.mayv.ctgate.repository

import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.Soldier
import com.mayv.ctgate.network.SMISApi
import com.mayv.ctgate.utils.funs.getExceptionFromStatusCode
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: SMISApi) {

    suspend fun searchForSoldiers(
        name: String,
        page: Int,
        size: Int,
        excludeRemoved: Boolean,
        authToken: String
    ): Resource<List<Soldier>> {

        val soldiersResource = Resource<List<Soldier>>()

        try {

            val response = api.searchForSoldiers(name, page, size, if(excludeRemoved) 1 else 0, authToken)
            soldiersResource.statusCode = response.code()

            if (response.isSuccessful) {
                soldiersResource.data = response.body()
            } else {
                soldiersResource.exception = getExceptionFromStatusCode(response.code())
            }

        } catch (exception: Exception) {
            soldiersResource.exception = getExceptionFromStatusCode(400)
            soldiersResource.statusCode = 400
        }

        return soldiersResource
    }

}