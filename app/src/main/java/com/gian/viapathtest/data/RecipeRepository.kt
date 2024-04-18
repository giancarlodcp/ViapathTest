package com.gian.viapathtest.data

import com.gian.viapathtest.data.model.RecipeInformationResponse
import com.gian.viapathtest.data.model.RecipeSearchResponse
import com.gian.viapathtest.data.network.RetrofitClient
import retrofit2.Response

class RecipeRepository() {
    private val apiKey = "222893607bcf4b1aa7efd88db90e8e6d"

    private val apiService = RetrofitClient.apiService
    suspend fun searchRecipes(query: String): Response<RecipeSearchResponse> {
        return apiService.searchRecipes(apiKey, query)
    }

    suspend fun getRecipeInformation(recipeId: Int): Response<RecipeInformationResponse> {
        return apiService.getRecipeInformation(recipeId, apiKey)
    }
}