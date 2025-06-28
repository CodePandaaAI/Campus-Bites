package com.pandawork.campusbites.ui.theme

import android.R.attr.value
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.pandawork.campusbites.ui.theme.components.MenuItemCard
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.ceil

@Composable
fun CheckoutScreen(viewModel: CampusBitesViewModel, onNavigationClick: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    val roundUp = BigDecimal(uiState.totalPrice.toString()) // Convert to String to avoid Double's precision issues
        .setScale(2, RoundingMode.UP) // Set scale to 2 and specify rounding mode
        .toDouble()
    Column(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.weight(1f)) {
            items(uiState.selectedMenuItems.values.toList()) { orderItem ->
                MenuItemCard(orderItem)
            }
        }
        Text("Total Price: $${roundUp}", fontSize = 32.sp)
        Button(onNavigationClick, modifier = Modifier.fillMaxWidth()) {
            Text("Go Back to Menu")
        }
    }
}