package com.ankur.inventorymanagement.ordermenegement.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ankur.inventorymanagement.ordermenegement.domain.business.User


@Composable
fun ProfileScreen(user: User,
                  getOrders:()->Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF2F2F2))
            .padding(16.dp)
    ) {
        // User Info Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(Modifier.padding(16.dp)) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.CenterHorizontally),
                    tint = Color(0xFF232F3E)
                )
                Spacer(Modifier.height(8.dp))
                Text(user.userName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(user.address.city+","+user.address.state+",("+user.address.pinCode+")", style = MaterialTheme.typography.bodyMedium)
                Text("+91 9876543210", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Spacer(Modifier.height(24.dp))

        // Option List
        ProfileOptionItem("My Orders", Icons.Default.ShoppingCart) {
//            Toast.makeText(context, ""+orderController.ordersOf(user.userId), Toast.LENGTH_SHORT).show()
              getOrders()
        }

        ProfileOptionItem("Address Book", Icons.Default.LocationOn) {
            Toast.makeText(context, "Your address: ${user.address.city}, ${user.address.state}", Toast.LENGTH_LONG).show()
        }

        ProfileOptionItem("Payment Methods", Icons.Default.Info) {
            Toast.makeText(context, "Coming soon...", Toast.LENGTH_SHORT).show()
        }

        ProfileOptionItem("Logout", Icons.Default.ExitToApp) {
            Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun ProfileOptionItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = title, tint = Color(0xFF232F3E))
            Spacer(Modifier.width(16.dp))
            Text(title, style = MaterialTheme.typography.titleMedium)
        }
    }
}
