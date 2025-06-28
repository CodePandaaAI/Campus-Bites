package com.pandawork.campusbites.ui.theme.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pandawork.campusbites.data.MenuItem
import com.pandawork.campusbites.data.OrderItem

@Composable
fun MenuItemCard(menuItem: MenuItem, isSelected: Boolean, onMenuItemClick: (MenuItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onMenuItemClick(menuItem) }, // Event goes UP!
        border = if (isSelected) BorderStroke(1.dp, Color.Blue.copy(alpha = 0.5f)) else null,
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.surface
        ),
    ) {
        Row {
            Image(
                painter = painterResource(menuItem.imageUrl),
                contentDescription = null,
                modifier = Modifier.size(125.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = menuItem.name, style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = menuItem.description, style = MaterialTheme.typography.titleMedium
                )
                Text(text = "Price: $${menuItem.price}", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
@Composable
fun MenuItemCard(orderItem: OrderItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.surface
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(orderItem.menuItem.imageUrl),
                contentDescription = null,
                modifier = Modifier.size(125.dp)
            )
            Text(
                text = "${orderItem.menuItem.name} x ${orderItem.quantity} = $${orderItem.menuItem.price * orderItem.quantity}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
@Preview
fun MenuItemCardPreview() {
    val menuItem = MenuItem(
        id = "1",
        name = "Burger",
        description = "A delicious burger",
        price = 5.99,
        categoryId = "1",
        imageUrl = com.pandawork.campusbites.R.drawable.burger_classic // Replace with your actual drawable resource
    )
    MenuItemCard(
        menuItem = menuItem,
        isSelected = false,
        onMenuItemClick = {}
    )
}

@Preview
@Composable
fun OrderItemCardPreview() {
    val menuItem = MenuItem(
        id = "1",
        name = "Pizza",
        description = "A tasty pizza",
        price = 10.99,
        categoryId = "2",
        imageUrl = com.pandawork.campusbites.R.drawable.pizza_margherita // Replace with your actual drawable resource
    )
    val orderItem = OrderItem(
        menuItem = menuItem,
        quantity = 2
    )
    MenuItemCard(orderItem = orderItem)
}