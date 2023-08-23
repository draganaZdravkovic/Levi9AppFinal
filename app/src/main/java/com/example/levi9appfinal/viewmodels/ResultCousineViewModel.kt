package com.example.levi9appfinal.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levi9appfinal.models.Restaurants
import com.example.levi9appfinal.repository.ResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultCousineViewModel(private val resultRepository: ResultRepository,
                             private val type: String): ViewModel() {
        init {
            viewModelScope.launch(Dispatchers.IO) {
                resultRepository.getResultByCousine(type)
            }
        }

        val result: LiveData<Restaurants>
            get() = resultRepository.result
    }
