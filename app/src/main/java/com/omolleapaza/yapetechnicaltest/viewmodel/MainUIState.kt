package com.omolleapaza.yapetechnicaltest.viewmodel

import com.omolleapaza.yapetechnicaltest.RecipeEntity


sealed class MainUIState {
    data class Success(val recipes: List<RecipeEntity>) : MainUIState()
    data class Error(val exception: Throwable?) : MainUIState()
}
