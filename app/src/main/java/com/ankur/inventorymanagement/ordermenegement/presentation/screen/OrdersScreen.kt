package com.ankur.inventorymanagement.ordermenegement.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ankur.inventorymanagement.ordermenegement.domain.business.Order
import com.ankur.inventorymanagement.ordermenegement.presentation.OrderViewModel

@Composable
fun OrdersScreen(
    vm: OrderViewModel,
    onClickInvoice:(Order)->Unit,
    onClickTrack :(Order)->Unit
    // for Track / Invoice navigation
) {
    val orders = remember { vm.getOrders() }

    if (orders.isEmpty()) {
        // Pretty empty‑state
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Outlined.ShoppingCart, null, Modifier.size(72.dp))
            Spacer(Modifier.height(16.dp))
            Text("You have no orders yet", style = MaterialTheme.typography.titleMedium)
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {

            items(orders, key = { it.orderId }) { order ->
                OrderCard(
                    order = order,
                    onTrack = onClickTrack,
                    onInvoice = onClickInvoice
                )
            }
        }
    }
}
