package com.ankur.inventorymanagement.utils

sealed class Screen(val route: String, val title: String) {
    object Plp      : Screen("home",     "My Store")
    object Profile  : Screen("profile",  "Profile")
    object Checkout : Screen("checkout", "Checkout")
    object Invoice  : Screen("invoice",  "Invoice")
    object Orders  : Screen("orders",  "Orders")
    object TrackOrder  : Screen("trackOrder",  "Track Order")
}
