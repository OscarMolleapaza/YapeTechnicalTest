package com.omolleapaza.yapetechnicaltest.viewmodel

import com.omolleapaza.yapetechnicaltest.MainCoroutineRule
import com.omolleapaza.yapetechnicaltest.data.RecipeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val coroutineTestRule: MainCoroutineRule = MainCoroutineRule()

    @RelaxedMockK
    lateinit var repository: RecipeRepository
    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(repository)

    }

    @Test
    fun getRecipes() = runTest {
        coEvery {
            repository.getRecipes()
        } returns Result.success(listOf())

        viewModel.getRecipes()
        viewModel.uiState.value.let {

            assert(it == MainUIState.Success(listOf()))
        }
    }
}