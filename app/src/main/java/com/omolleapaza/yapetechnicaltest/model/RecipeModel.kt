package com.omolleapaza.yapetechnicaltest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeModel(
    val title: String,
    val imageUrl: String,
    val description: String,
    val author: String,
    val latitude: Double,
    val longitude: Double,
    val locationName: String,
    val score: Double
):Parcelable