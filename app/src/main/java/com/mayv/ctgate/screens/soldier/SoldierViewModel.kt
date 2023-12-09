package com.mayv.ctgate.screens.soldier

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var isDataLoading by mutableStateOf(false)
    var isDataSuccessful by mutableStateOf(false)

    var isImgLoading by mutableStateOf(false)
    var isImgSuccessful by mutableStateOf(false)

    fun soldierImage(nationalId: Long, token: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            if (nationalId.toString().isEmpty()) return@launch

            isImgSuccessful = false
            isImgLoading = true

            _image.value = soldierRepository.getSoldierImage(nationalId, token)

            val statusCode = _image.value.statusCode

            isImgSuccessful = statusCode == 200 || statusCode == 201
            isImgLoading = false
        }
    }

    fun soldierData(nationalId: Long, token: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            if (nationalId.toString().isEmpty()) return@launch

            isDataSuccessful = false
            isDataLoading = true

            _data.value = soldierRepository.getSoldierData(nationalId, token)

            val statusCode = _data.value.statusCode

            isDataSuccessful = statusCode == 200 || statusCode == 201
            isDataLoading = false
        }
    }

}