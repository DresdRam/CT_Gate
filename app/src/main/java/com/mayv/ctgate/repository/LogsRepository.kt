package com.mayv.ctgate.repository

import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.GateLog
import com.mayv.ctgate.network.SMISApi
import com.mayv.ctgate.utils.funs.getExceptionFromStatusCode
import javax.inject.Inject

class LogsRepository @Inject constructor(private val api: SMISApi) {

    suspend fun getAllLogs(page: Int, size: Int, token: String): Resource<List<GateLog>> {

        val allLogsResource: Resource<List<GateLog>> = Resource()

        try {
            val response = api.getAllGateLogs(page, size, token)
            allLogsResource.statusCode = response.code()

            if (response.isSuccessful) {
                allLogsResource.data = response.body()
            } else {
                allLogsResource.exception = getExceptionFromStatusCode(response.code())
            }
        } catch (exception: Exception) {
            allLogsResource.exception = getExceptionFromStatusCode(400)
            allLogsResource.statusCode = 400
        }

        return allLogsResource
    }

    suspend fun getSoldierLogs(nationalId: Long, token: String): Resource<List<GateLog>> {

        val soldierLogsResource: Resource<List<GateLog>> = Resource()

        try {
            val response = api.getSoldierLogs(nationalId, token)
            soldierLogsResource.statusCode = response.code()

            if (response.isSuccessful) {
                soldierLogsResource.data = response.body()
            } else {
                soldierLogsResource.exception = getExceptionFromStatusCode(response.code())
            }
        } catch (exception: Exception) {
            soldierLogsResource.exception = getExceptionFromStatusCode(400)
            soldierLogsResource.statusCode = 400
        }

        return soldierLogsResource
    }

}