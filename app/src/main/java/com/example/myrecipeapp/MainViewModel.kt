package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categoryState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoryState

    //on ViewModel creation call fetchCategories to get data as fast as possible
    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        //viewModelScope provides the ability to execute async code (called a coroutine)
        viewModelScope.launch {
            try {
                //call getCategories which is part of the service that calls the API
                val response = recipeService.getCategories()
                //update the state with the category data
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    list = response.categories,
                    error = null
                )
            }
            catch (e: Exception) {
                //update state to reflect the result of the API call
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "Error fetching categories ${e.message}"
                )
            }
        }
    }

    data class RecipeState (
        val loading : Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}