package com.pandawork.campusbites.ui.theme

import androidx.lifecycle.ViewModel
import com.pandawork.campusbites.data.CampusBitesUiState
import com.pandawork.campusbites.data.MenuItem
import com.pandawork.campusbites.data.OrderItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CampusBitesViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(CampusBitesUiState())
    val uiState: StateFlow<CampusBitesUiState> = _uiState.asStateFlow()

    fun toggleMenuItemSelected(menuItem: MenuItem) {
        val currentSelections = _uiState.value.selectedMenuItems.toMutableMap()
        if(currentSelections.containsKey(menuItem.id)){
            currentSelections.remove(menuItem.id)
        } else {
            currentSelections[menuItem.id] = OrderItem(menuItem, 1)
        }
        _uiState.value = _uiState.value.copy(selectedMenuItems = currentSelections)
    }
}