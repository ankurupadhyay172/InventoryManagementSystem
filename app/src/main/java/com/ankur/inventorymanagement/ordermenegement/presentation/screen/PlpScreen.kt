package com.ankur.inventorymanagement.ordermenegement.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ankur.inventorymanagement.inventorymanagement.domain.business.ProductCategory
import com.ankur.inventorymanagement.ordermenegement.presentation.CartViewModel


@Composable
fun PlpScreen(
    cartVM: CartViewModel,
    onCheckoutClick: (totalItems: Int, totalPrice: Double) -> Unit
) {
    val context = LocalContext.current
    val categories by cartVM.categories.collectAsState()
    val quantities by cartVM.quantities.collectAsState()

    // ✅ Show toast messages from ViewModel
    LaunchedEffect(Unit) {
        cartVM.uiEvents.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    // ✅ Calculate totals
    val totalItems = quantities.values.sum()
    val totalPrice = quantities.entries.sumOf { entry ->
        val category = categories.find { it.productCategoryId == entry.key }
        (category?.price ?: 0.0) * entry.value
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 72.dp) // Leave space for checkout button
            ) {
                items(categories, key = { it.productCategoryId }) { category ->
                    val qty = quantities[category.productCategoryId] ?: 0
                    CategoryItem(
                        productCategory = category,
                        qty = qty,
                        onAdd = {
                            cartVM.add(category.productCategoryId){
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        } },
                        onRemove = { cartVM.remove(category.productCategoryId) }
                    )
                }
            }
        }

        // ✅ Checkout Button
        if (totalItems > 0) {
            Button(
                onClick = { onCheckoutClick(totalItems, totalPrice) },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA41C), // Amazon orange
                    contentColor = Color.Black
                )
            ) {
                Text("Checkout ($totalItems items) - ₹${"%.2f".format(totalPrice)}")
            }
        }
    }
}

@Composable
fun CategoryItem(
    productCategory: ProductCategory,
    qty: Int,
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    val isOutOfStock = productCategory.products.isEmpty()

    Card(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(productCategory.productCategoryName, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text(
                    "₹${productCategory.price}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            when {
                isOutOfStock -> {
                    Text(
                        "Out of Stock",
                        color = Color.Red,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }

                qty == 0 -> {
                    Button(
                        onClick = onAdd,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFC107),
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Add to Cart")
                    }
                }

                else -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        IconButton(onClick = onRemove) {
                            Icon(Icons.Default.Delete, contentDescription = "−")
                        }
                        Text(qty.toString(), style = MaterialTheme.typography.titleMedium)
                        IconButton(onClick = onAdd) {
                            Icon(Icons.Default.Add, contentDescription = "+")
                        }
                    }
                }
            }
        }
    }
}
