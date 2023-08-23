package com.example.levi9appfinal.models

data class Result(
    val attribution: List<Attribution>,
    val booking_info: BookingInfo,
    val coordinates: Coordinates,
    val id: String,
    val images: List<Image>,
    val location_id: String,
    val name: String,
    val price_tier: Int,
    val score: Double,
    val snippet: String,
    val snippet_language_info: Any
)