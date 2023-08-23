package com.example.levi9appfinal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levi9appfinal.repository.ResultRepository

class ResultViewModelFactory(private val resultRepository: ResultRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ResultViewModel(resultRepository) as T
    }
}