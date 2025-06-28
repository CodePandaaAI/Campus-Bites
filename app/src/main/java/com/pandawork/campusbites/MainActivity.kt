package com.pandawork.campusbites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pandawork.campusbites.ui.theme.CampusBiteScreen
import com.pandawork.campusbites.ui.theme.CampusBitesTheme
import com.pandawork.campusbites.ui.theme.CampusBitesViewModel
import com.pandawork.campusbites.ui.theme.CheckoutScreen

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

@Preview
@Composable
fun PreviewCampusBitesApp() {
    // ViewModel creation is handled by the default parameter in CampusBiteScreen
    Scaffold { it ->
        CampusBitesApp(modifier = Modifier.padding(it))
    }
}
