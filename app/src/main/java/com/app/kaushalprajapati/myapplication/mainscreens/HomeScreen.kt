package com.app.kaushalprajapati.myapplication.mainscreens

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.kaushalprajapati.myapplication.R
import com.app.kaushalprajapati.myapplication.payments.PaymentRazorpay
import com.app.kaushalprajapati.myapplication.tabscreen.TabFragmentWithPaging

@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    var walletBalance by remember { mutableDoubleStateOf(100.0) }
    var searchText by remember { mutableStateOf("") }

    // Launcher for PaymentRazorpay Activity
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val updatedBalance = result.data?.getDoubleExtra("updatedBalance", walletBalance) ?: walletBalance
            walletBalance = updatedBalance // Update the wallet balance
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
    ) {
        // Search Bar
        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .height(60.dp)
                .background(color = Color(0xFF1E1E1E), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp)// Proper padding inside box
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left Profile Icon
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile Icon",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            navController.navigate("profile_screen")
                        },
                    tint = Color.Magenta.copy(alpha = 0.5f)
                )

                // Search Bar
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .padding(horizontal = 8.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.Gray
                        )
                    },
                    placeholder = { Text("Search...", color = Color.White) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF2E2E2E),
                        unfocusedContainerColor = Color(0xFF1E1E1E),
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(30.dp),
                    singleLine = true
                )

                // Right Notification Icon
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notification Icon",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            navController.navigate("notification_screen")
                        },
                    tint = Color.Magenta.copy(alpha = 0.5f)
                )
            }
        }

        // Card Section (Features & Wallet)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(200.dp)
                .background(color = Color(0xFF1E1E1E), shape = RoundedCornerShape(14.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // **Feature Icons Row**
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FeatureItem(R.drawable.instantbuy, "Instant Buy") { navController.navigate("instant_buy") }
                    FeatureItem(R.drawable.portfolio, "Earn Extra") { navController.navigate("earn_extra") }
                    FeatureItem(R.drawable.plant, "Start SIP") { navController.navigate("start_sip") }
                    FeatureItem(R.drawable.learning, "Learning") { navController.navigate("learning") }
                }

                // **Wallet Balance & Add Funds**
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.wallet),
                            contentDescription = "Funds Add",
                            modifier = Modifier.size(40.dp),
                            tint = Color.White
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Funds  = ",
                            style = TextStyle(
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp,
                                letterSpacing = 1.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = "₹$walletBalance",
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp
                            )
                        )
                    }

                    FloatingActionButton(
                        onClick = {
                            val intent = Intent(context, PaymentRazorpay::class.java)
                            launcher.launch(intent)
                        },
                        containerColor = Color.Magenta.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .padding(16.dp)
                            .size(55.dp)
                    ) {
                        Text(
                            text = "+",
                            style = TextStyle(
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }

        // Tab Fragment with Paging
        Spacer(modifier = Modifier.height(5.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF1E1E1E))
        ) {
            TabFragmentWithPaging()
        }
    }
}

// **Composable for Feature Icons**
@Composable
fun FeatureItem(iconRes: Int, title: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            modifier = Modifier.size(40.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = title,
            style = TextStyle(color = Color.Gray, fontSize = 14.sp)
        )
    }
}
