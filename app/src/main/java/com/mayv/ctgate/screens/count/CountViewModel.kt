package com.mayv.ctgate.screens.count

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.GateLog
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
    var isLoading by mutableStateOf(false)
    var isSuccessful by mutableStateOf(false)

    fun soldierLogs(nationalId: Long, token: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            if (nationalId.toString().isEmpty()) return@launch

            isSuccessful = false
            isLoading = true

            _data.value = logsRepository.getSoldierLogs(nationalId, token)

            val code = _data.value.statusCode

            isSuccessful = code == 200 || code == 201
            isLoading = false
        }
    }

    fun allLogs(page: Int = 1, size: Int = 20, token: String = "") {
        viewModelScope.launch(Dispatchers.IO) {

            isSuccessful = false
            isLoading = true

            _data.value = logsRepository.getAllLogs(page, size, token)

            val code = _data.value.statusCode

            isSuccessful = code == 200 || code == 201
            isLoading = false
        }
    }

}