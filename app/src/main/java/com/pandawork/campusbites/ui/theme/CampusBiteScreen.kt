package com.pandawork.campusbites.ui.theme

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
import com.pandawork.campusbites.ui.theme.components.MenuItemCard

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
