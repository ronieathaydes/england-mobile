package com.england.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.england.FieldService
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainScope = MainScope()
    private val fieldService = FieldService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = "Loading..."

        mainScope.launch {
            runCatching {
                fieldService.getFields()
            }.onSuccess { result ->
                tv.text = result.joinToString(separator = "\n") { field -> field.name }
            }.onFailure { error ->
                tv.text = "Error: ${error.localizedMessage}"
            }
        }
    }
}
