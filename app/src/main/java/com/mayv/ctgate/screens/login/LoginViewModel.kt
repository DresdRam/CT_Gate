package com.mayv.ctgate.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.Authorization
import com.mayv.ctgate.model.GateLog
import com.mayv.ctgate.model.Login
import com.mayv.ctgate.repository.LoginRepository
import com.mayv.ctgate.repository.LogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _data = MutableStateFlow(Resource<Authorization>())
    val data: StateFlow<Resource<Authorization>> = _data
    var isLoading by mutableStateOf(false)
    var isSuccessful by mutableStateOf(false)
    var token by mutableStateOf("")

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (username.isEmpty() || password.isEmpty()) return@launch
            isLoading = true

            val login = Login(username, password)

            _data.value = loginRepository.login(login)

            val statusCode = _data.value.statusCode

            if(statusCode == 200 || statusCode == 201){
                token = _data.value.data!!.access_token
                isSuccessful = true
            }

            isLoading = false
        }
    }

}