package com.ankur.inventorymanagement.ordermenegement.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ankur.inventorymanagement.ordermenegement.domain.business.Order
import com.ankur.inventorymanagement.ordermenegement.domain.business.OrderStatus
import java.time.format.DateTimeFormatter
@Composable
fun TrackOrderScreen(order: Order) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {



        // Title
        Text(
            "Track Order",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(Modifier.height(12.dp))

        // Order Info
        Text("Order ID: #${order.orderId}", style = MaterialTheme.typography.bodyMedium)
        Text(
            "Ordered on: ${order.orderDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"))}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(32.dp))

        // Tracking Steps
        val steps = listOf(
            OrderStatus.PROCESS,
            OrderStatus.OUT_FOR_DELIVERY,
            OrderStatus.DELIVERED
        )

        steps.forEachIndexed { index, status ->
            val isCompleted = order.status.ordinal >= status.ordinal

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                    contentDescription = status.label,
                    tint = if (isCompleted) Color(0xFF1A8F53) else Color.Gray
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    status.label,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = if (isCompleted) FontWeight.SemiBold else FontWeight.Normal
                    )
                )
            }

            // Vertical Connector Line
            if (index != steps.lastIndex) {
                Box(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .width(2.dp)
                        .height(32.dp)
                        .background(Color.LightGray)
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        // Delivery Date
        val deliveryDate = order.orderDate.plusDays(3).format(DateTimeFormatter.ofPattern("dd MMM yyyy"))

        Text(
            if (order.status == OrderStatus.DELIVERED)
                "Delivered on: $deliveryDate"
            else
                "Expected delivery: $deliveryDate",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = if (order.status == OrderStatus.DELIVERED) Color(0xFF1A8F53) else Color.Unspecified
            )
        )
    }
}
