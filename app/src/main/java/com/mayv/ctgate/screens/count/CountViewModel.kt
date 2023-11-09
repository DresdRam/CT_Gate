package com.mayv.ctgate.screens.count

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.GateLog
import com.mayv.ctgate.model.Soldier
import com.mayv.ctgate.repository.LogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountViewModel @Inject constructor(private val logsRepository: LogsRepository) :
    ViewModel() {

    private val _data = MutableStateFlow(Resource<List<GateLog>>())
    val data: StateFlow<Resource<List<GateLog>>> = _data

    fun soldierLogs(nationalId: Long) {
        viewModelScope.launch {
            if (nationalId.toString().isEmpty()) return@launch

            _data.value = logsRepository.getSoldierLogs(nationalId)

        }
    }

    fun allLogs(page: Int = 1, size: Int = 20) {
        viewModelScope.launch {

            _data.value = logsRepository.getAllLogs(page, size)

        }
    }

}