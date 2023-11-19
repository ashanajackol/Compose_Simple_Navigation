package com.ashana.compose.navigation.dummy

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PersonalDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EnterDetailUiState())
    val uiState: StateFlow<EnterDetailUiState> = _uiState.asStateFlow()

    fun addName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = name
            )
        }
    }

    fun addAddress(address: String) {
        _uiState.update {
            it.copy(
                address = address
            )
        }
    }

    fun clearAddress() {
        _uiState.update {
            it.copy(
                address = ""
            )
        }
    }
}