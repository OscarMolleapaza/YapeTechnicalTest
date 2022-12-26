package com.omolleapaza.yapetechnicaltest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class RecipeEntity(
    val title: String,
    val imageUrl: String,
    val description: String,
    val author: String,
    val latitude: Double,
    val longitude: Double,
    val locationName: String
)