package com.example.sklepallegro.network

data class Offer(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val price: Price,
    val description: String
)