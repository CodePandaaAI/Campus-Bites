package com.pandawork.campusbites.data

data class MenuItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val categoryId: String,
    val imageUrl: String
)