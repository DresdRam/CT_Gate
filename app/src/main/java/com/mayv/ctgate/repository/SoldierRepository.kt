package com.mayv.ctgate.repository

import com.mayv.ctgate.data.DataOrException
import com.mayv.ctgate.model.Soldier
import com.mayv.ctgate.network.SMISApi
import javax.inject.Inject

class SoldierRepository @Inject constructor(private val api: SMISApi) {

    private val soldierDataOrException = DataOrException<Soldier, Boolean, Exception>()

    suspend fun getSoldierData(nationalId: Int): DataOrException<Soldier, Boolean, Exception> {

        try {
            soldierDataOrException.loading = true
            soldierDataOrException.data = api.getSoldier(nationalId)
            if(soldierDataOrException.data.toString().isNotEmpty()) soldierDataOrException.loading = false
        }catch (exception: Exception){
            soldierDataOrException.exception = exception
        }

        return soldierDataOrException
    }

}