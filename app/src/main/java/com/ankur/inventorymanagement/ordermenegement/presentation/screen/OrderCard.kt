package com.ankur.inventorymanagement.ordermenegement.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ankur.inventorymanagement.ordermenegement.domain.business.Order
import com.ankur.inventorymanagement.ordermenegement.domain.business.OrderStatus
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun OrderCard(
    order: Order,
    onTrack: (Order) -> Unit,
    onInvoice: (Order) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            /* ───────────── ① Status & Date ───────────── */
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = order.status.label,
                    style = MaterialTheme.typography.labelMedium,
                    color = when (order.status) {
                        OrderStatus.DELIVERED        -> Color(0xFF1A8F53)
                        OrderStatus.OUT_FOR_DELIVERY -> Color(0xFFFFA41C)
                        else                         -> MaterialTheme.colorScheme.primary
                    }
                )
                Text(
                    text = order.orderDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(Modifier.height(8.dp))
            Divider()
            Spacer(Modifier.height(8.dp))

            /* ───────────── ② Item summary (max 2) ───────────── */
            Column {
                order.items.take(2).forEach { item ->
                    Text(
                        text = "${item.qty} × ${item.title}",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1
                    )
                }
                if (order.items.size > 2) {
                    Text(
                        text = "+${order.items.size - 2} more item(s)",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }

            Spacer(Modifier.height(8.dp))
            Divider()
            Spacer(Modifier.height(8.dp))

            /* ───────────── ③ Pricing (key–value column) ───────────── */
            Column {
                PriceRow(label = "Subtotal", value = formatINRCurrency(order.subtotal))
                if (order.discountAmount > 0) {
                    PriceRow(
                        label = "Discount",
                        value = "-${formatINRCurrency(order.discountAmount)}",
                        valueColor = Color(0xFF2E7D32)
                    )
                }
                PriceRow(label = "Tax (10 %)", value = formatINRCurrency(order.taxAmount))
                Spacer(Modifier.height(4.dp))
                PriceRow(
                    label = "Total",
                    value = formatINRCurrency(order.total),
                    bold = true
                )
            }

            Spacer(Modifier.height(12.dp))
            Divider()
            Spacer(Modifier.height(12.dp))

            /* ───────────── ④ Actions ───────────── */
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionButton(
                    text = "Track",
                    icon = Icons.Outlined.LocationOn,
                    onClick = { onTrack(order) }
                )
                ActionButton(
                    text = "Invoice",
                    icon = Icons.Outlined.List,
                    onClick = { onInvoice(order) }
                )
            }
        }
    }
}

/* ---------- small reusable helpers ---------- */

@Composable
private fun PriceRow(
    label: String,
    value: String,
    valueColor: Color = MaterialTheme.colorScheme.onSurface,
    bold: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodySmall)
        Text(
            value,
            style = if (bold)
                MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            else
                MaterialTheme.typography.bodySmall,
            color = valueColor
        )
    }
}

@Composable
private fun ActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier // 👈 allow parent to pass Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(icon, contentDescription = null, Modifier.size(18.dp))
        Spacer(Modifier.width(6.dp))
        Text(text)
    }
}


/* ---------- INR formatting helper ---------- */
fun formatINRCurrency(amount: Double): String {
    val fmt = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    return fmt.format(amount)
}

