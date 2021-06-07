package com.england.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.england.FieldService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val fieldService = FieldService()

    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow: StateFlow<UiState> = _uiStateFlow

    data class UiState(
        val content: String = "",
    )

    init {
        viewModelScope.launch {
            _uiStateFlow.value = _uiStateFlow.value.copy(content = "Loading...")

            runCatching {
                fieldService.getFields()
            }.onSuccess { result ->
                val content = result.joinToString(separator = "\n") { field -> field.name }
                _uiStateFlow.value = _uiStateFlow.value.copy(content = content)
            }.onFailure { error ->
                val content = "Error: ${error.localizedMessage}"
                _uiStateFlow.value = _uiStateFlow.value.copy(content = content)
            }
        }
    }
}
