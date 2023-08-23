package com.example.levi9appfinal.models

data class Image(
    val attribution: AttributionX,
    val caption: String,
    val sizes: Sizes,
    val source_id: String,
    val source_url: String
)