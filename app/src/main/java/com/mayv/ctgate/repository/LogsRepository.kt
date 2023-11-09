package com.mayv.ctgate.repository

import android.util.Log
import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.GateLog
import com.mayv.ctgate.network.SMISApi
import javax.inject.Inject

class LogsRepository @Inject constructor(private val api: SMISApi) {

    private val allLogsResource: Resource<List<GateLog>> = Resource()

    private val soldierLogsResource: Resource<List<GateLog>> = Resource()

    suspend fun getAllLogs(page: Int, size: Int): Resource<List<GateLog>> {
        try {
            allLogsResource.loading = true
            allLogsResource.data = api.getAllGateLogs(page, size)
            Log.i("TAG", "getAllLogs: ${api.getAllGateLogs(page, size)}")
            allLogsResource.loading = false
        }catch (exception: Exception){
            allLogsResource.loading = false
            allLogsResource.failed = true
            allLogsResource.exception = exception
        }

        return allLogsResource
    }

    suspend fun getSoldierLogs(nationalId: Long): Resource<List<GateLog>> {

        try {
            soldierLogsResource.loading = true
            soldierLogsResource.data = api.getSoldierLogs(nationalId)
            soldierLogsResource.loading = false
        }catch (exception: Exception){
            soldierLogsResource.loading = false
            soldierLogsResource.failed = true
            soldierLogsResource.exception = exception
        }

        return soldierLogsResource
    }

}