package com.example.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//builds a connection to the baseUrl to receive and convert json data from the api to Gson for kotlin
private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

//This allows retrofit to access service functions in the ApiService class
val recipeService = retrofit.create(ApiService::class.java)

interface ApiService {
    //use retrofit to define the api endpoint and the request type
    //suspend keyword allows for asynchronous execution
    @GET("categories.php")
    suspend fun getCategories() : CategoriesResponse
}