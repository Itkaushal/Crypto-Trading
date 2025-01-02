package com.app.kaushalprajapati.myapplication.mainscreens

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.app.kaushalprajapati.myapplication.payments.PaymentRazorpay
import com.app.kaushalprajapati.myapplication.tabscreen.TabFragmentWithPaging


@Composable
fun HomeScreen(navController: NavHostController) {

    val context = LocalContext.current
    var walletBalance by remember { mutableStateOf(100.0) }

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
            .background(Color.Black)
    ) {

        // Search Bar and more content in to search bar.........
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .background(color = Color.White)
                .height(60.dp)
                .background(color = Color.Black),
            border = BorderStroke(1.dp, color = Color.Blue),
            shape = RoundedCornerShape(20.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left Profile Icon
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile Icon",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                // Search Bar
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .padding(horizontal = 10.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Search",
                            style = MaterialTheme.typography.displaySmall.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        )
                    }
                }

                // Right Notification Icon
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notification Icon",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }


        // card design for add funds and more...................
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .background(color = Color.White)
                .height(200.dp)
                .background(color = Color.Black),
            border = BorderStroke(1.dp, color = Color.Blue),
            shape = RoundedCornerShape(30.dp),

            ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // first item............
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Instant Buy",
                        modifier = Modifier
                            .size(50.dp)
                    )

                    Text(
                        text = "Instant Buy",
                        style = TextStyle(color = Color.Black, fontSize = 16.sp)
                    )
                }

                // second items........
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Instant Buy",
                        modifier = Modifier
                            .size(50.dp)
                    )

                    Text(
                        text = "Instant Buy",
                        style = TextStyle(color = Color.Black, fontSize = 16.sp)
                    )
                }

                // third items..........
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Instant Buy",
                        modifier = Modifier
                            .size(50.dp)
                    )

                    Text(
                        text = "Instant Buy",
                        style = TextStyle(color = Color.Black, fontSize = 16.sp)
                    )
                }

                // fourth items...............
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Instant Buy",
                        modifier = Modifier
                            .size(50.dp)
                    )

                    Text(
                        text = "Instant Buy",
                        style = TextStyle(color = Color.Black, fontSize = 16.sp)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Funds Add",
                        modifier = Modifier
                            .size(30.dp)
                            .align(alignment = Alignment.CenterVertically)
                    )

                    Text(
                        text = "Funds",
                        style = TextStyle(
                            color = Color.Blue,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(10.dp)
                    )

                    Text(
                        text = "₹$walletBalance",
                        style = TextStyle(
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(10.dp)
                    )


                    FloatingActionButton(
                        onClick = {
                            val intent = Intent(context, PaymentRazorpay::class.java)
                            launcher.launch(intent)
                        },
                        containerColor = Color.Red,
                        shape = RoundedCornerShape(100.dp),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .padding(10.dp)
                            .size(40.dp)
                            .offset(x = 100.dp)
                    ) {
                        Text(
                            text = "+",
                            style = TextStyle(
                                color = Color.Blue,
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp
                            )
                        )
                    }


                }
            }

        }

        // tab fragment with paging..........

        Spacer(modifier = Modifier.height(5.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TabFragmentWithPaging()
        }
    }

}

