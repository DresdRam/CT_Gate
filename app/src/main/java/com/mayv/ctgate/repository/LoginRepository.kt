package com.mayv.ctgate.repository

import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.Authorization
import com.mayv.ctgate.model.Login
import com.mayv.ctgate.network.SMISApi
import com.mayv.ctgate.utils.funs.getExceptionFromStatusCode
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api: SMISApi) {

    suspend fun login(login: Login): Resource<Authorization> {

        val authResource = Resource<Authorization>()

        try {

            val response = api.login(login)
            authResource.statusCode = response.code()

            if (response.isSuccessful) {
                authResource.data = response.body()
            } else {
                authResource.exception = getExceptionFromStatusCode(response.code())
            }

        } catch (exception: Exception) {
            authResource.exception = getExceptionFromStatusCode(400)
            authResource.statusCode = 400
        }

        return authResource
    }

}