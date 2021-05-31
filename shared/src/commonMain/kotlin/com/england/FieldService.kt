package com.england

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText

class FieldService {

    suspend fun getFields(): String {
        val urlString = "https://england.rvichetti.dev/fields"
        val httpClient = HttpClient()
        val response: HttpResponse = httpClient.get(urlString)
        return response.readText()
    }
}
