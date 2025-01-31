package com.example.myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Make categories serialized when transferred between different places, this is important for passing complex objects through navigation
@Parcelize
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
):Parcelable

data class CategoriesResponse(val categories: List<Category>)