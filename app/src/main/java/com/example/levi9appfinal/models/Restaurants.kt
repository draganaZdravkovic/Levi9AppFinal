package com.example.levi9appfinal.models

data class Restaurants(
    val is_deprecated: String,
    val more: Boolean,
    val results: List<Result>
)