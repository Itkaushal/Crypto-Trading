package com.app.kaushalprajapati.myapplication.cardScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.kaushalprajapati.myapplication.R

// Sample Notification Data Model
data class NotificationItem(val title: String, val message: String, val time: String)

// Sample Notification List
val notifications = listOf(
    NotificationItem("Payment Received", "You received ₹5,000 in your account.", "10 min ago"),
    NotificationItem("Withdrawal Success", "Your ₹1,000 withdrawal was successful.", "1 hour ago"),
    NotificationItem("New Offer!", "Get 10% cashback on your next trade.", "2 hours ago"),
    NotificationItem("Security Alert", "New login detected from a different device.", "Yesterday"),
    NotificationItem("Referral Bonus", "You earned ₹250 from your friend's signup!", "2 days ago")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Dark mode UI
    ) {
        // **Top Bar**
        TopAppBar(
            title = { Text("Notifications", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
            navigationIcon = {
                androidx.compose.material.IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E1E1E))
        )

        Spacer(modifier = Modifier.height(10.dp))

        // **Notification List**
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(notifications) { notification ->
                NotificationCard(notification)
            }
        }
    }
}

// **Single Notification Card**
@Composable
fun NotificationCard(notification: NotificationItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // **Notification Icon**
            Image(
                painter = painterResource(id = R.drawable.notification), // Replace with your own drawable
                contentDescription = "Notification Icon",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // **Notification Content**
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = notification.title,
                    style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = notification.message,
                    style = TextStyle(color = Color.Gray, fontSize = 14.sp)
                )
            }

            // **Notification Timestamp**
            Text(
                text = notification.time,
                style = TextStyle(color = Color.LightGray, fontSize = 12.sp),
                textAlign = TextAlign.End
            )
        }
    }
}
