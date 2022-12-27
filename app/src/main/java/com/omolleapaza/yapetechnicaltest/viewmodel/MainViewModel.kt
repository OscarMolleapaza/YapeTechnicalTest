package com.omolleapaza.yapetechnicaltest.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omolleapaza.yapetechnicaltest.data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUIState>(MainUIState.Success(emptyList()))
    val uiState: StateFlow<MainUIState> = _uiState

    private fun getRecipes() {
        viewModelScope.launch {
            val result = recipeRepository.getRecipes()
            if (result.isSuccess) {
                _uiState.value = MainUIState.Success(result.getOrDefault(emptyList()))
            } else {
                _uiState.value = MainUIState.Error(result.exceptionOrNull())
            }
        }
    }

    init {
        getRecipes()
    }

}