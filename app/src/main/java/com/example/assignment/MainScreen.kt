package com.example.assignment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import androidx.compose.material3.Switch

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.responseState

    var showDrinks by remember { mutableStateOf(false) }  // Toggle state

    Box(modifier = modifier.fillMaxSize()) {
        when {
            viewstate.loading -> {
                Text(text = "Loading", modifier.align(Alignment.Center))
            }

            viewstate.error != null -> {
                Text(text = "Error Occurred", modifier.align(Alignment.Center))
            }

            else -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    // Toggle Switch Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (showDrinks) "Showing Drinks" else "Showing Meals",
                            modifier = Modifier.weight(1f),
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                        Switch(
                            checked = showDrinks,
                            onCheckedChange = { newValue -> showDrinks = newValue  }
                        )
                    }

                    // Display Meals or Drinks based on toggle state
                    LazyColumn {
                        if (!showDrinks) {
                            items(viewstate.mealList) { meal ->
                                mealItem(category = meal)
                            }
                        } else {
                            items(viewstate.drinkList) { drink ->
                                drinkItem(category = drink)
                            }
                        }
                    }
                }
            }
        }
    }
}

// Meal Item
@Composable
fun mealItem(category: MealCategory) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

// Drink Item
@Composable
fun drinkItem(category: DrinkCategory) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = category.strDrinkThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text = category.strDrink,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}