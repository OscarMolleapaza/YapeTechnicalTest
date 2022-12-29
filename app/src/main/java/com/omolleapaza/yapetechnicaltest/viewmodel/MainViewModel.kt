package com.omolleapaza.yapetechnicaltest.viewmodel

import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.google.android.gms.maps.model.LatLng
import com.omolleapaza.yapetechnicaltest.data.RecipeRepository
import com.omolleapaza.yapetechnicaltest.model.RecipeModel
import com.omolleapaza.yapetechnicaltest.ui.extensions.convertToLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUIState>(MainUIState.Success(emptyList()))
    val uiState: StateFlow<MainUIState> = _uiState


    val title: MutableLiveData<String> = MutableLiveData()
    val description: MutableLiveData<String> = MutableLiveData()
    val author: MutableLiveData<String> = MutableLiveData()
    val locationName: MutableLiveData<String> = MutableLiveData()
    val score: MutableLiveData<String> = MutableLiveData()



    fun getRecipes() {
        viewModelScope.launch {
            val result = recipeRepository.getRecipes()
            if (result.isSuccess) {
                _uiState.value = MainUIState.Success(result.getOrDefault(emptyList()))
            } else {
                _uiState.value = MainUIState.Error(result.exceptionOrNull())
            }
        }
    }
    fun setupData(data: RecipeModel) {
        title.value = data.title
        description.value = data.description
        author.value = data.author
        locationName.value = data.locationName
        score.value = data.score.toString()
    }


    init {
        getRecipes()
    }

}