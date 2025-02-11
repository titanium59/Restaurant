package com.example.assignment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier){
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.responseState

    Box(modifier = modifier.fillMaxSize()){
        when{
            viewstate.loading -> {
                Text(text = "Loading" , modifier.align(Alignment.Center))
            }
            viewstate.error != null -> {
                Text(text = "Error Occured" , modifier.align(Alignment.Center))
            }
            else -> {
                LazyColumn {
                    items(viewstate.mealList) { meal ->
                        Text("Meal: ${meal.strCategory}")
                    }
                    items(viewstate.drinkList) { drink ->
                        Text("Drink: ${drink.strDrink}")
                    }
                }
            }
        }
    }
}