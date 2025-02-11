package com.example.assignment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerEffect() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(8.dp))
                .shimmer()  // Apply shimmer effect
                .background(Color.Gray.copy(alpha = 0.3f))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(20.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmer()
                .background(Color.Gray.copy(alpha = 0.3f))
        )
    }
}
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewstate: MainViewModel.ApiResponseState,
    navigateToMealDetail: (MealCategory) -> Unit,
    navigateToDrinkDetail: (DrinkCategory) -> Unit) {

    var showDrinks by remember { mutableStateOf(false) }  // Toggle state

    Box(modifier = modifier.fillMaxSize()) {
        when {
            viewstate.loading -> {
                LazyColumn {
                    items(5) { ShimmerEffect() }  // Show 5 shimmer placeholders while loading
                }
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
                                mealItem(category = meal , navigateToMealDetail)
                            }
                        } else {
                            items(viewstate.drinkList) { drink ->
                                drinkItem(category = drink , navigateToDrinkDetail)
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
fun mealItem(
        category: MealCategory,
        navigateToMealDetail: (MealCategory) -> Unit
        ) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable { navigateToMealDetail(category) },
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
fun drinkItem(
        category: DrinkCategory,
        navigateToDrinkDetail: (DrinkCategory) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable { navigateToDrinkDetail(category) },
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