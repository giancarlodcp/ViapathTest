package com.gian.viapathtest.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeInformationResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("readyInMinutes") val readyInMinutes: Int,
    @SerializedName("summary") val summary: String,
    @SerializedName("instructions") val instructions: String,
    @SerializedName("extendedIngredients") val ingredientList: List<Ingredients>,
    @SerializedName("aggregateLikes") val likes: Int,
    @SerializedName("spoonacularScore") val score: Double
) : Parcelable