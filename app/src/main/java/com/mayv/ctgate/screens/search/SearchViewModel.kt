package com.mayv.ctgate.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayv.ctgate.data.Resource
import com.mayv.ctgate.model.Soldier
import com.mayv.ctgate.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private val _data = MutableStateFlow(Resource<List<Soldier>>())
    val data: StateFlow<Resource<List<Soldier>>> = _data
    var isLoading by mutableStateOf(false)
    var isSuccessful by mutableStateOf(false)

    fun search(
        name: String,
        page: Int = 1,
        size: Int = 20,
        excludeRemoved: Boolean = false,
        authToken: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (name.isEmpty() || authToken.isEmpty()) return@launch
            isLoading = true

            _data.value = searchRepository.searchForSoldiers(name, page, size, excludeRemoved, authToken)

            val statusCode = _data.value.statusCode

            isSuccessful = statusCode == 200 || statusCode == 201

            isLoading = false
        }
    }

}