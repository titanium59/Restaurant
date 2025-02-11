package com.example.assignment

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface MealService{
    @GET("categories.php")
    suspend fun getMealCategories(): CategoriesResponse
}

private val mealRetrofit = Retrofit.Builder()
    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val mealService = mealRetrofit.create(MealService::class.java)

interface DrinksService{
    @GET("filter.php?c=Ordinary_Drink")
    suspend fun getDrinksCategories(): DrinksResponse
}

private val drinkRetrofit = Retrofit.Builder()
    .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val drinksService = drinkRetrofit.create(DrinksService::class.java)