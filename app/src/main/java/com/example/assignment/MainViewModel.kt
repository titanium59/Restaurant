package com.example.assignment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _responseState = mutableStateOf(ApiResponseState())
    val responseState: State<ApiResponseState> = _responseState

    init{
        fetchMealAndDrinks()
    }
    private fun fetchMealAndDrinks(){
        viewModelScope.launch {
            try{
                val mealResponse = mealService.getMealCategories()
                val drinksResponse = drinksService.getDrinksCategories()
                _responseState.value = _responseState.value.copy(
                    loading = false,
                    mealList = mealResponse.categories,
                    drinkList = drinksResponse.drinks,
                    error = null
                )
            }catch(e: Exception){
                _responseState.value.copy(
                    loading = false,
                    error = "Error Fetching Categories ${e.message}"
                )
            }
        }
    }
    data class ApiResponseState(
        val loading: Boolean = true,
        val mealList: List<MealCategory> = emptyList(),
        val drinkList: List<DrinkCategory> = emptyList(),
        val error: String? = null
    )
}
