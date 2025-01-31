package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(viewstate = viewstate, navigateToDetail = {
                //Get the screen state (the highest screen on the back stack)
                //savedStateHandle provides an interface for transferring state between screens through jetpack navigation
                //set puts a key-value pair into savedStateHandle, on details it can be accessed using key="cat"
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route = Screen.DetailScreen.route) {
            val category = navController.
            previousBackStackEntry?. //Weve moved screens so now get the previous screen
            savedStateHandle?.       //Use the savedStateHandle which stores the data from that screen
            get<Category>("cat") ?: Category("", "", "","") //Get from the handle using the key set in RecipeScreen
            CategoryDetailScreen(category)
        }
    }
}