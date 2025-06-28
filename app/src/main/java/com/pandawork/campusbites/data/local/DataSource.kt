package com.pandawork.campusbites.data.local


import com.pandawork.campusbites.R
import com.pandawork.campusbites.data.Category
import com.pandawork.campusbites.data.MenuItem

object DataSource {
    val categories = listOf(
        Category("cat_1", "Burgers"),
        Category("cat_2", "Pizzas"),
        Category("cat_3", "Drinks"),
        Category("cat_4", "Desserts")
    )
    val menuItems = listOf(
        MenuItem(
            id = "item_1",
            name = "Classic Burger",
            description = "Juicy beef patty with lettuce, tomato, and cheese",
            price = 8.99,
            categoryId = "cat_1",
            imageUrl = R.drawable.burger_classic
        ),
        MenuItem(
            id = "item_2",
            name = "Veggie Burger",
            description = "Plant-based patty with fresh veggies",
            price = 7.99,
            categoryId = "cat_1",
            imageUrl = R.drawable.burger_veggie
        ),
        MenuItem(
            id = "item_3",
            name = "Pepperoni Pizza",
            description = "Classic pepperoni with mozzarella",
            price = 12.50,
            categoryId = "cat_2",
            imageUrl = R.drawable.pizza_pepperoni
        ),
        MenuItem(
            id = "item_4",
            name = "Margherita Pizza",
            description = "Tomato, mozzarella, and basil",
            price = 11.00,
            categoryId = "cat_2",
            imageUrl = R.drawable.pizza_margherita
        ),
        MenuItem(
            id = "item_5",
            name = "Cola",
            description = "Refreshing carbonated drink",
            price = 2.00,
            categoryId = "cat_3",
            imageUrl = R.drawable.drink_cola
        ),
        MenuItem(
            id = "item_6",
            name = "Orange Juice",
            description = "Freshly squeezed orange juice",
            price = 3.50,
            categoryId = "cat_3",
            imageUrl = R.drawable.drink_orange_juice
        )
    )
}