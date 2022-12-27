package com.omolleapaza.yapetechnicaltest.data.remote

import android.util.Log
import com.omolleapaza.yapetechnicaltest.RecipeEntity
import com.omolleapaza.yapetechnicaltest.data.RecipeRepository
import com.omolleapaza.yapetechnicaltest.data.remote.dto.RecipeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class RecipeRemoteDataSource(
    private val remoteApi: RemoteApi,
    private val json: Json
) : RecipeRepository {

    override suspend fun getRecipes(): Result<List<RecipeEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val response = remoteApi.callService(remoteApi.buildGetRequest("/recipe"))

                Log.i("Entrando",response.toString())
                if (response.isSuccessful) {
                    val body = response.body?.string() ?: ""
                    val recipeResponse =
                        json.decodeFromString<RecipeResponse>(body)
                    val recipes = recipeResponse.data?.map {
                        RecipeEntity(
                            title = it.title ?: "",
                            imageUrl = it.imageUrl ?: "",
                            description = it.description ?: "",
                            author = it.author ?: "",
                            latitude = it.latitude ?: 0.0,
                            longitude = it.longitude ?: 0.0,
                            locationName = it.locationName ?: "",
                            score = it.score?:0.0

                        )
                    } ?: emptyList()
                    Result.success(recipes)
                } else {
                    Result.failure(Exception("Error ${response.code}  ${response.message}"))
                }
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }
    

}