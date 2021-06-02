package com.england

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
)
