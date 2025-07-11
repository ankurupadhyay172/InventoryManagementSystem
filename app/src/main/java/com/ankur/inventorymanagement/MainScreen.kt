package com.ankur.inventorymanagement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ankur.inventorymanagement.ordermenegement.presentation.CartViewModel
import com.ankur.inventorymanagement.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(cartVM: CartViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Plp.route
    val canPop = navController.previousBackStackEntry != null

    val screen = when {
        currentRoute.startsWith(Screen.Checkout.route) -> Screen.Checkout
        currentRoute.startsWith(Screen.Profile.route)  -> Screen.Profile
        currentRoute.startsWith(Screen.Invoice.route)  -> Screen.Invoice
        currentRoute.startsWith(Screen.Orders.route)  -> Screen.Orders
        currentRoute.startsWith(Screen.TrackOrder.route)  -> Screen.TrackOrder
        else                                           -> Screen.Plp
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF232F3E),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                navigationIcon = {
                    if (canPop && screen != Screen.Plp) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(screen.title)
                    }
                },
                actions = {
                    if (screen != Screen.Profile&&screen==Screen.Plp) {
                        IconButton(onClick = {
                            navController.navigate(Screen.Profile.route) {
                                popUpTo(Screen.Profile.route)
                                launchSingleTop = true
                            }
                        }) {
                            Icon(Icons.Default.Person, contentDescription = "Profile")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            AppNavGraph(
                navController = navController,
               cartVM
            )
        }
    }
}
