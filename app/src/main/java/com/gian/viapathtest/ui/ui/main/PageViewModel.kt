package com.gian.viapathtest.ui.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.gian.viapathtest.data.RecipeRepository
import com.gian.viapathtest.data.model.RecipeInformationResponse
import com.gian.viapathtest.data.model.RecipeResult
import com.gian.viapathtest.ui.viewmodel.Resource
import kotlinx.coroutines.launch

class PageViewModel : ViewModel() {

    private val _recipe = MutableLiveData<Resource<RecipeInformationResponse>>()
    val recipe: LiveData<Resource<RecipeInformationResponse>> = _recipe

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getRecipeInformation(recipeId: Int) {
        viewModelScope.launch {
            _recipe.value = Resource.Loading()
            try {
                val repository =  RecipeRepository()
                val response = repository.getRecipeInformation(recipeId)
                val body = response.body()
                if (response.isSuccessful && body!=null) {
                    _recipe.value = Resource.Success(body)
                } else {
                    _recipe.value =
                        Resource.Error("Failed to get recipe information: ${response.code()}")
                }
            } catch (e: Exception) {
                _recipe.value =
                    Resource.Error("Failed to get recipe information: ${e.message}")
            }
        }
    }


    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = _index.map {
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}