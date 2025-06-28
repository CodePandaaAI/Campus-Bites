package com.pandawork.campusbites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pandawork.campusbites.data.MenuItem
import com.pandawork.campusbites.data.OrderItem
import com.pandawork.campusbites.ui.theme.CampusBitesTheme
import com.pandawork.campusbites.ui.theme.CampusBitesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusBitesTheme {
                Scaffold { it ->
                    CampusBitesApp(modifier = Modifier.padding(it))
                }
            }
        }
    }
}

@Composable
fun CampusBitesApp(modifier: Modifier) {
    val navController = rememberNavController()

    val viewmodel: CampusBitesViewModel = viewModel()

    NavHost(startDestination = "menu", navController = navController, modifier = modifier) {
        composable(route = "menu") {
            CampusBiteScreen(
                viewModel = viewmodel,
                onNavigationClick = { navController.navigate("checkout") }
            )
        }
        composable("checkout") {
            CheckoutScreen(
                viewmodel,
                onNavigationClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun CampusBiteScreen(
    viewModel: CampusBitesViewModel,
    onNavigationClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.weight(1f)) {
            items(uiState.menuItems) { menuItem ->
                val isSelected = uiState.selectedMenuItems.containsKey(menuItem.id)
                MenuItemCard(
                    menuItem,
                    isSelected,
                    onMenuItemClick = { clickedMenuItem ->
                        viewModel.toggleMenuItemSelected(
                            clickedMenuItem
                        )
                    }
                )
            }
        }
        Button(onNavigationClick, modifier = Modifier.fillMaxWidth()) {
            Text("Go to checkout")
        }
    }
}

@Composable
fun CheckoutScreen(viewModel: CampusBitesViewModel, onNavigationClick: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    Column(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.weight(1f)) {
            items(uiState.selectedMenuItems.values.toList()) { orderItem ->
                MenuItemCard(orderItem)
            }
        }
        Text("Total Price: $${uiState.totalPrice}", fontSize = 32.sp)
        Button(onNavigationClick, modifier = Modifier.fillMaxWidth()) {
            Text("Go Back to Menu")
        }
    }
}

@Composable
fun MenuItemCard(menuItem: MenuItem, isSelected: Boolean, onMenuItemClick: (MenuItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onMenuItemClick(menuItem) }, // Event goes UP!
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color.LightGray else MaterialTheme.colorScheme.surface
        )
    ) {
        Row {
            Image(
                painter = painterResource(menuItem.imageUrl),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = menuItem.name, style = MaterialTheme.typography.displaySmall
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
        Row {
            Image(
                painter = painterResource(orderItem.menuItem.imageUrl),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = "${orderItem.menuItem.name} x ${orderItem.quantity} = $${orderItem.menuItem.price * orderItem.quantity}",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun CampusBiteScreenPreview() {
    // ViewModel creation is handled by the default parameter in CampusBiteScreen
    Scaffold { it ->
        CampusBitesApp(modifier = Modifier.padding(it))
    }
}

@Preview
@Composable
fun MenuItemCardPreview() {
    val menuItem = MenuItem(
        id = "1",
        name = "Cheeseburger",
        description = "A delicious cheeseburger with all the fixings.",
        price = 5.99,
        categoryId = "burgers",
        imageUrl = 1
    )
    MenuItemCard(menuItem = menuItem, isSelected = false, onMenuItemClick = {})
}
