package com.app.kaushalprajapati.myapplication.mainscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.kaushalprajapati.myapplication.R


@Composable
fun PortfolioScreen() {
    val portfolioValue = "$25,000"
    val changePercentage = "+5.23%"
    val holdings = listOf(
        CryptoHolding("Bitcoin", "BTC", "$12,000", "+3.5%", R.drawable.bitcoin),
        CryptoHolding("Ethereum", "ETH", "$8,500", "-1.2%", R.drawable.etherium),
        CryptoHolding("Cardano", "ADA", "$4,500", "+2.8%", R.drawable.cardano)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black) // Dark background
            .padding(16.dp)
    ) {
        // Portfolio Value Section
        PortfolioHeader(portfolioValue = portfolioValue, changePercentage = changePercentage)

        Spacer(modifier = Modifier.height(24.dp))

        // Holdings Section
        Text(
            text = "Your Holdings",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn {
            items(holdings) { holding ->
                CryptoHoldingCard(holding)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Recent Transactions
        Text(
            text = "Recent Transactions",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn {
            items(3) { index ->
                TransactionItem(
                    title = "Bought Bitcoin",
                    amount = "-$500",
                    time = "2 hours ago",
                    iconResId = R.drawable.bitcoin
                )
            }
        }
    }
}

@Composable
fun PortfolioHeader(portfolioValue: String, changePercentage: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Portfolio Value",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Gray
        )
        Text(
            text = portfolioValue,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = changePercentage,
            style = MaterialTheme.typography.bodyLarge,
            color = if (changePercentage.startsWith("+")) Color.Green else Color.Red
        )
    }
}

@Composable
fun CryptoHoldingCard(holding: CryptoHolding) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(holding.iconResId),
            contentDescription = holding.name,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = holding.name, color = Color.White, style = MaterialTheme.typography.titleLarge)
            Text(text = holding.symbol, color = Color.Gray, style = MaterialTheme.typography.titleLarge)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = holding.value, color = Color.White, style = MaterialTheme.typography.titleMedium)
            Text(
                text = holding.change,
                color = if (holding.change.startsWith("+")) Color.Green else Color.Red,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
fun TransactionItem(title: String, amount: String, time: String, iconResId: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(iconResId),
            contentDescription = title,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, color = Color.White, style = MaterialTheme.typography.titleLarge)
            Text(text = time, color = Color.Gray, style = MaterialTheme.typography.titleMedium)
        }
        Text(
            text = amount,
            color = if (amount.startsWith("-")) Color.Red else Color.Green,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

data class CryptoHolding(
    val name: String,
    val symbol: String,
    val value: String,
    val change: String,
    val iconResId: Int
)

