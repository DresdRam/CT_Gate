package com.mayv.ctgate.screens.soldier

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayv.ctgate.data.DataOrException
import com.mayv.ctgate.model.Soldier
import com.mayv.ctgate.repository.SoldierRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SoldierViewModel @Inject constructor(private val soldierRepository: SoldierRepository) : ViewModel() {

    var soldierData: MutableState<DataOrException<Soldier, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception()))

    var soldierImage: MutableState<DataOrException<Bitmap, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception()))

    fun soldierImage(nationalId: Long) {
        viewModelScope.launch {
            soldierImage.value = soldierRepository.getSoldierImage(nationalId)
        }
    }

    fun soldierInfo(nationalId: Long) {
        viewModelScope.launch {
            soldierData.value = soldierRepository.getSoldierData(nationalId)
        }
    }

}