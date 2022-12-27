package com.omolleapaza.yapetechnicaltest.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    @SerialName("Title") val title: String?,
    @SerialName("ImageUrl") val imageUrl: String?,
    @SerialName("Description") val description: String?,
    @SerialName("Author") val author: String?,
    @SerialName("Latitude") val latitude: Double?,
    @SerialName("Longitude") val longitude: Double?,
    @SerialName("LocationName") val locationName: String?,
    @SerialName("Score") val score: Double?
)