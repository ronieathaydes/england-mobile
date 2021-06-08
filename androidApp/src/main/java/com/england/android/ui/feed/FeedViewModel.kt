package com.england.android.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.england.FieldService
import com.england.android.arch.UiState
import com.england.android.arch.UiStateImpl
import kotlinx.coroutines.launch

class FeedViewModel(
    private val fieldService: FieldService = FieldService()
) : ViewModel(), UiState<FeedUiState> by UiStateImpl(FeedUiState()) {

    init {
        viewModelScope.launch {
            setState { copy(content = "Loading...") }

            runCatching {
                fieldService.getFields()
            }.onSuccess { result ->
                val content = result.joinToString(separator = "\n") { field -> field.name }
                setState { copy(content = content) }
            }.onFailure { error ->
                val content = "Error: ${error.localizedMessage}"
                setState { copy(content = content) }
            }
        }
    }
}
