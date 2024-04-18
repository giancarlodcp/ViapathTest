package com.gian.viapathtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gian.viapathtest.data.RecipeRepository
import com.gian.viapathtest.data.model.RecipeResult
import kotlinx.coroutines.launch

class RecipeListViewModel() : ViewModel() {
    private val _recipes = MutableLiveData<Resource<List<RecipeResult>>>()
    val recipes: LiveData<Resource<List<RecipeResult>>> = _recipes

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            _recipes.value = Resource.Loading()
            try {
                val repository =  RecipeRepository()
                val result = repository.searchRecipes(query = query)
                if (result.isSuccessful) {
                    _recipes.value = Resource.Success(result.body()?.results ?: emptyList())
                } else {
                    _errorMessage.value = "Error on recipes: ${result.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error on recipes: ${e.message}"
            }
        }
    }
}

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String) : Resource<T>()
}