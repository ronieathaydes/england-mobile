package com.england

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class FieldService {

    @Throws(Exception::class)
    suspend fun getFields(): List<Field> {
        val urlString = "https://england.rvichetti.dev/fields"
        val httpClient = HttpClient {
            install(JsonFeature) {
                val json = Json { ignoreUnknownKeys = true }
                serializer = KotlinxSerializer(json)
            }
        }
        val fields: List<FieldResponse> = httpClient.get(urlString)
        return fields.map { field -> Field(name = field.name) }
    }
}
