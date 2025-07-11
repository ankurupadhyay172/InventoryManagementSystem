package com.ankur.inventorymanagement.ordermenegement.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ankur.inventorymanagement.ordermenegement.domain.business.CardPaymentMode
import com.ankur.inventorymanagement.ordermenegement.domain.business.CashOnDeliveryMode
import com.ankur.inventorymanagement.ordermenegement.domain.business.NetBankingMode
import com.ankur.inventorymanagement.ordermenegement.domain.business.Order
import com.ankur.inventorymanagement.ordermenegement.domain.business.PaymentMode
import com.ankur.inventorymanagement.ordermenegement.domain.business.UpiPaymentMode

@Composable
fun CheckoutScreen(
    order: Order?,
    onPlaceOrder: (order:Order?) -> Unit
) {
    val context = LocalContext.current
    var selectedPayment by remember { mutableStateOf("Cash on Delivery") }
    val paymentOptions = listOf("Cash on Delivery", "UPI", "Credit/Debit Card", "Net Banking")
// Calculate totals from the order map
    val totalItems = order?.productCategoryIdVsCountMap?.values?.sum()?:0
    val totalAmount = order?.productCategoryIdVsCountMap?.entries?.sumOf { (catId, qty) ->
        val price = order.warehouse.inventory.getProductCategoryFromId(catId)?.price ?: 0.0
        price * qty
    }

    // ↓ helper mapping
    fun toMode(name: String): PaymentMode = when (name) {
        "UPI"                -> UpiPaymentMode()
        "Credit/Debit Card"  -> CardPaymentMode()
        "Net Banking"        -> NetBankingMode()
        else                 -> CashOnDeliveryMode
    }

    LaunchedEffect(Unit) {
        order?.setPaymentMode(toMode(selectedPayment))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Address Section
        Text("Deliver To", style = MaterialTheme.typography.titleMedium)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(Modifier.padding(16.dp)) {
                Text(order?.user?.userName?:"")
                Text(order?.address?.city?:"")
                Text(order?.address?.state+"("+order?.address?.pinCode+")")
                Text("Phone: +91 9876543210")
            }
        }

        Spacer(Modifier.height(12.dp))

        // Payment Section
        Text("Payment Method", style = MaterialTheme.typography.titleMedium)
        paymentOptions.forEach { method ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = selectedPayment == method,
                        onClick = { selectedPayment = method
                            order?.setPaymentMode(toMode(method))
                        }
                    )
                    .padding(vertical = 8.dp)
            ) {
                RadioButton(
                    selected = selectedPayment == method,
                    onClick = {
                        selectedPayment = method
                    order?.setPaymentMode(toMode(method))
                    }
                )
                Text(text = method, modifier = Modifier.padding(start = 8.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

        // Order Summary
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("Order Summary", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                Text("Items: $totalItems")
                Text("Total: ₹${"%.2f".format(totalAmount)}")
            }
        }

        Spacer(Modifier.weight(1f)) // Push button to bottom

        // Place Order Button
        Button(
            onClick = {
                Toast.makeText(context, "Order Placed Successfully!", Toast.LENGTH_SHORT).show()
                onPlaceOrder(order)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC107), // Amazon yellow
                contentColor = Color.Black
            )
        ) {
            Text("Place Order")
        }
    }
}
