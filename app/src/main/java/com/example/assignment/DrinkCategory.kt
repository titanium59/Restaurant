package com.example.assignment

data class DrinkCategory(
    val strDrink: String,
    val strDrinkThumb: String,
    val idDrink: String
)

data class DrinksResponse(val drinks : List<DrinkCategory>)
