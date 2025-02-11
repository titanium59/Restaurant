package com.example.assignment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DrinkCategory(
    val strDrink: String,
    val strDrinkThumb: String,
    val idDrink: String
):Parcelable

data class DrinksResponse(val drinks : List<DrinkCategory>)
