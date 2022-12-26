package com.omolleapaza.yapetechnicaltest.data

import com.omolleapaza.yapetechnicaltest.RecipeEntity

interface RecipeRepository {
    suspend fun getRecipes(): Result<List<RecipeEntity>>
}