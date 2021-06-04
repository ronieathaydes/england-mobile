package com.england.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.england.FieldService
import com.england.android.theme.EnglandTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val fieldService = FieldService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()
            val content = remember { mutableStateOf("Loading...") }
            scope.launch {
                runCatching {
                    fieldService.getFields()
                }.onSuccess { result ->
                    content.value = result.joinToString(separator = "\n") { field -> field.name }
                }.onFailure { error ->
                    content.value = "Error: ${error.localizedMessage}"
                }
            }
            MainScreen(content.value)
        }
    }
}

@Composable
fun MainScreen(content: String) {
    EnglandTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = content)
        }
    }
}
