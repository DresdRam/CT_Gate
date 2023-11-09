package com.mayv.ctgate.screens.soldier

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.Soldier
import com.mayv.ctgate.repository.SoldierRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SoldierViewModel @Inject constructor(private val soldierRepository: SoldierRepository) :
    ViewModel() {

    private val _data = MutableStateFlow(Resource<Soldier>())
    val data: StateFlow<Resource<Soldier>> = _data

    private val _image = MutableStateFlow(Resource<Bitmap>())
    val image: StateFlow<Resource<Bitmap>> = _image

    fun soldierImage(nationalId: Long) {
        viewModelScope.launch {
            if (nationalId.toString().isEmpty()) return@launch

            _image.value = soldierRepository.getSoldierImage(nationalId)

            soldierRepository.checkConnection()
        }
    }

    fun soldierData(nationalId: Long) {
        viewModelScope.launch(Dispatchers.Main) {
            if (nationalId.toString().isEmpty()) return@launch

            _data.value = soldierRepository.getSoldierData(nationalId)

        }
    }

}