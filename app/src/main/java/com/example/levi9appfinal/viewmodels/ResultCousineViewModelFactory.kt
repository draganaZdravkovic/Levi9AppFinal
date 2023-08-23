package com.example.levi9appfinal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levi9appfinal.repository.ResultRepository

class ResultCousineViewModelFactory(private val resultRepository: ResultRepository,
                                    private val type: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ResultCousineViewModel(resultRepository, type) as T
    }
}