package com.pandawork.campusbites.data

import com.pandawork.campusbites.data.local.DataSource

data class CampusBitesUiState(
    val categories: List<Category> = emptyList(),
    val menuItems: List<MenuItem> = DataSource.menuItems,
    val selectedCategory: Category? = null,
    val selectedMenuItems: Map<String, OrderItem> = emptyMap(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    val totalPrice: Double = selectedMenuItems.values.sumOf { orderItem ->
        orderItem.menuItem.price * orderItem.quantity
    }
}