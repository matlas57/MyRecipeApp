package com.example.myrecipeapp

sealed class Screen(val route: String) { //sealed enforces that all derived classes are declared in the same file, so types can be matched at compile time
    //here we configure navigation roots so that changes are reflected across the entire app safely
    object RecipeScreen:Screen("recipescreen")
    object DetailScreen:Screen("detailscreen")
}