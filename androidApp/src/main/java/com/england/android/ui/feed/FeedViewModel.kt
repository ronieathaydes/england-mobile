package com.england.android.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.england.FieldService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeedViewModel(
    private val fieldService: FieldService = FieldService()
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(FeedUiState())
    val uiStateFlow: StateFlow<FeedUiState> = _uiStateFlow

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
