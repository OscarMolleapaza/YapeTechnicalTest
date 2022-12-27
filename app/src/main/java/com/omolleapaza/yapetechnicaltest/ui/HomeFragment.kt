package com.omolleapaza.yapetechnicaltest.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.omolleapaza.yapetechnicaltest.R
import com.omolleapaza.yapetechnicaltest.RecipeEntity
import com.omolleapaza.yapetechnicaltest.data.RecipeRepository
import com.omolleapaza.yapetechnicaltest.data.remote.RecipeRemoteDataSource
import com.omolleapaza.yapetechnicaltest.data.remote.RemoteApi
import com.omolleapaza.yapetechnicaltest.extensions.createFactory
import com.omolleapaza.yapetechnicaltest.ui.adapter.RecipeAdapter
import com.omolleapaza.yapetechnicaltest.viewmodel.MainUIState
import com.omolleapaza.yapetechnicaltest.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class HomeFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    private val recipeRepository: RecipeRepository by lazy {
        RecipeRemoteDataSource(RemoteApi(), Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        })
    }

    private val viewModel by viewModels<MainViewModel> {
        MainViewModel(recipeRepository).createFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvRecipes)
        recyclerView?.adapter = RecipeAdapter(emptyList())

        observerUiState()
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun observerUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is MainUIState.Success -> {
                            render(uiState.recipes)
                        }
                        is MainUIState.Error -> {
                            showError(uiState.exception)
                        }
                    }
                }
            }
        }
    }

    private fun showError(throwable: Throwable?) {
        Log.v("ERROR", "throwable $throwable")
    }

    private fun render(recipes: List<RecipeEntity>) {
        (recyclerView?.adapter as? RecipeAdapter)?.update(recipes)
    }



}