package com.england

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FieldResponse(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("address") val address: String,
    @SerialName("city") val city: String,
    @SerialName("state") val state: String,
    @SerialName("neighborhood") val neighborhood: String,
    @SerialName("number") val number: String,
    @SerialName("location") val location: LocationResponse,
)
