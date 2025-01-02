package com.app.kaushalprajapati.myapplication.mainscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.kaushalprajapati.myapplication.cryptoApi.CryptoScreen

@Composable
fun MarketScreen(){
    Column(modifier = Modifier
        .background(color = Color.Black)
        .padding(top = 20.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center) {
        Text(text = "Market Screen🪙💵",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            letterSpacing = 2.sp,
            color = Color.Red,
            modifier = Modifier.padding(10.dp),
            fontSize = 30.sp)

        CryptoScreen()
    }
}