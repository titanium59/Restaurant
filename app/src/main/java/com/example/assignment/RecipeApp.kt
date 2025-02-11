package com.example.assignment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.responseState

    NavHost(navController = navController, startDestination = Screen.MealScreen.route){
        composable(route = Screen.MealScreen.route){
            MainScreen(
                viewstate = viewstate,
                navigateToMealDetail = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat" , it)
                    navController.navigate(Screen.MealDetailScreen.route)
                },
                navigateToDrinkDetail = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat" , it)
                    navController.navigate(Screen.DrinkDetailScreen.route)
                })
        }
        composable(route = Screen.MealDetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<MealCategory>("cat") ?: MealCategory("" , "" , "" , "")
            MealdetailScreen(category = category)
        }
        composable(route = Screen.DrinkDetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<DrinkCategory>("cat") ?: DrinkCategory("" , "" , "")
            DrinkDetailScreen(category = category)
        }

    }

}