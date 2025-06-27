package com.pandawork.campusbites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pandawork.campusbites.data.MenuItem
import com.pandawork.campusbites.ui.theme.CampusBitesTheme
import com.pandawork.campusbites.ui.theme.CampusBitesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusBitesTheme {
                Scaffold { it ->
                    CampusBiteScreen(modifier = Modifier.padding(it))
                }
            }
        }
    }
}


@Composable
fun CampusBiteScreen(modifier: Modifier = Modifier, viewmodel: CampusBitesViewModel = viewModel()) {
    val uiState by viewmodel.uiState.collectAsState()
    LazyColumn(modifier = modifier) {
        items(uiState.menuItems) { menuItem ->
            val isSelected = uiState.selectedMenuItems.containsKey(menuItem.id)
            MenuItemCard(
                menuItem,
                isSelected,
                onMenuItemClick = { clickedMenuItem ->
                    viewmodel.toggleMenuItemSelected(
                        clickedMenuItem
                    )
                }
            )
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
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = menuItem.name, style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = menuItem.description, style = MaterialTheme.typography.bodySmall
            )
            Text(text = "Price: $${menuItem.price}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview
@Composable
fun CampusBiteScreenPreview() {
    // ViewModel creation is handled by the default parameter in CampusBiteScreen
    CampusBiteScreen()
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
        imageUrl = "burger_classic"
    )
    MenuItemCard(menuItem = menuItem, isSelected = false, onMenuItemClick = {})
}
