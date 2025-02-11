package com.example.assignment

sealed class Screen(val route: String) {
    object MealScreen:Screen("mealscreen")
    object MealDetailScreen:Screen("mealdetailscreen")
    object DrinkDetailScreen:Screen("drinkdetailscreen")

}