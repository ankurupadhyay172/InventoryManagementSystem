package com.ankur.inventorymanagement.ordermenegement.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ankur.inventorymanagement.ordermenegement.domain.business.Order

@Composable
fun InvoiceScreen(order: Order?, onBack: () -> Unit) {
    val invoice = order?.invoice
    val scroll = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scroll)
    ) {
        Text("Invoice", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))

        // Address
        Text("Delivered To", style = MaterialTheme.typography.titleMedium)
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                order?.address.run {
                    Text(order?.user?.userName.toString())
                    Text(order?.user?.address?.city+","+order?.user?.address?.state)
                    Text(order?.user?.address?.pinCode.toString())
                    Text("Phone: +91 9876543210")
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Items Section
        Text("Items", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(vertical = 8.dp))

        invoice?.items?.forEach { item ->
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(item.categoryName, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "₹${"%.2f".format(item.unitPrice)} x ${item.quantity}",
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                        )
                    }

                    Text(
                        text = "₹${"%.2f".format(item.subtotal)}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                }

                Divider(modifier = Modifier.padding(top = 8.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

        // Summary
        Text("Order Summary", style = MaterialTheme.typography.titleMedium)
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Total: ₹${"%.2f".format(invoice?.totalAmount?.toDouble())}")
                Text("Tax (10% GST): ₹${"%.2f".format(invoice?.tax?.toDouble())}")
                Text(
                    "Final Amount: ₹${"%.2f".format(invoice?.finalAmount?.toDouble())}",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF232F3E))
        ) {
            Text("Back to Home", color = Color.White)
        }
    }
}
