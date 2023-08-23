package com.example.levi9appfinal.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.levi9appfinal.api.ApiInterface
import com.example.levi9appfinal.models.Restaurants

class ResultRepository(private val apiInterface: ApiInterface) {
    private val resultLiveData = MutableLiveData<Restaurants>()

    val result: LiveData<Restaurants>
    get() = resultLiveData
    suspend fun getResult() {
        val apiResult = apiInterface.getRestaurants("cuisine")
        if(apiResult.body() != null) {
            resultLiveData.postValue(apiResult.body())
        }
    }

    suspend fun getResultByCousine(type:String) {
        val apiResult = apiInterface.getRestaurantsByCousine("cuisine-${type}")
        if(apiResult.body() != null) {
            resultLiveData.postValue(apiResult.body())
        }
    }

}