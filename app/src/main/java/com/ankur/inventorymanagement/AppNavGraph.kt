package com.ankur.inventorymanagement

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ankur.inventorymanagement.ordermenegement.presentation.CartViewModel
import com.ankur.inventorymanagement.ordermenegement.presentation.screen.CheckoutScreen
import com.ankur.inventorymanagement.ordermenegement.presentation.screen.InvoiceScreen
import com.ankur.inventorymanagement.ordermenegement.presentation.OrderViewModel
import com.ankur.inventorymanagement.ordermenegement.presentation.screen.OrdersScreen
import com.ankur.inventorymanagement.ordermenegement.presentation.screen.PlpScreen
import com.ankur.inventorymanagement.ordermenegement.presentation.screen.ProfileScreen
import com.ankur.inventorymanagement.ordermenegement.presentation.screen.TrackOrderScreen
import com.ankur.inventorymanagement.utils.Screen



@Composable
fun AppNavGraph(
    navController: NavHostController,
    cartViewModel: CartViewModel
) {
    val orderViewModel: OrderViewModel = hiltViewModel()
    NavHost(navController, startDestination = Screen.Plp.route) {

        composable(Screen.Plp.route) {
            PlpScreen(cartViewModel) { items, price ->
                orderViewModel.prepareOrder()
                navController.navigate(Screen.Checkout.route)
            }
        }

        composable(Screen.Checkout.route) {
                CheckoutScreen(order = orderViewModel.currentOrder) {
                    orderViewModel.checkout(it)
                    cartViewModel.clearCart()
                    navController.navigate(Screen.Invoice.route)
            }
        }

        composable(Screen.Profile.route) {
            val user = cartViewModel.user // Or expose a `user` field from ViewModel
            ProfileScreen(user = user) {
                navController.navigate(Screen.Orders.route)
            }
        }
        composable(Screen.Invoice.route) {
            InvoiceScreen(orderViewModel.currentOrder) {
                navController.navigate(Screen.Plp.route){popUpTo(0)}

            }
        }

        composable(Screen.Orders.route) {
            OrdersScreen(vm = orderViewModel,{
                navController.navigate(Screen.Invoice.route)

            }){
                navController.navigate(Screen.TrackOrder.route)
            }
        }
        
        composable(Screen.TrackOrder.route){
            TrackOrderScreen(order = orderViewModel.currentOrder!!)
        }
    }
}


