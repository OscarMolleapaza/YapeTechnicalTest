package com.omolleapaza.yapetechnicaltest.data.remote


import com.omolleapaza.yapetechnicaltest.data.RecipeRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.serialization.json.Json
import org.junit.Before


class RecipeRemoteDataSourceTest {

    lateinit var recipeRepository: RecipeRepository

    @RelaxedMockK
    lateinit var json: Json

    @RelaxedMockK
    lateinit var remoteApi: RemoteApi

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        recipeRepository = RecipeRemoteDataSource(remoteApi,json)
    }

    /*
    @Test
    fun getRecipes() {
        every {
            remoteApi.buildGetRequest("/recipe")
        } returns Request.Builder().apply {
            get()
            url(URL.plus("/recipe"))
        }.build()

        every {
            remoteApi.callService(remoteApi.buildGetRequest("/recipe"))
        } returns
    }

     */
}