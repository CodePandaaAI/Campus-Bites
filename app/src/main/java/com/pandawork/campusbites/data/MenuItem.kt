package com.pandawork.campusbites.data

import androidx.annotation.DrawableRes

data class MenuItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val categoryId: String,
    val imageUrl: Int
)