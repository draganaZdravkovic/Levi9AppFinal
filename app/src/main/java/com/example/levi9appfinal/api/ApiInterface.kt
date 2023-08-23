package com.example.levi9appfinal.api

import com.example.levi9appfinal.models.Restaurants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers(
        "X-Triposo-Account: E9CHL2VB",
        "X-Triposo-Token: 81vio7opy5th0q8z5b3ch7whyophexp0"
    )
    @GET("/api/20220411/poi.json?location_id=Paris")
    suspend fun getRestaurants(
        @Query("tag_labels") type:String
    ): Response<Restaurants>

    @Headers(
        "X-Triposo-Account: E9CHL2VB",
        "X-Triposo-Token: 81vio7opy5th0q8z5b3ch7whyophexp0"
    )
    @GET("/api/20220411/poi.json?location_id=Paris")
    suspend fun getRestaurantsByCousine(
        @Query("tag_labels") type: String
    ): Response<Restaurants>
}