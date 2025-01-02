package com.app.kaushalprajapati.myapplication.tabscreen
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.rememberAsyncImagePainter
import com.app.kaushalprajapati.myapplication.cryptoApi.CryptoCoin
import com.app.kaushalprajapati.myapplication.cryptoApi.RetrofitInstance
import kotlinx.coroutines.launch


// Trending coins data in to tab with pager............
@Composable
fun Trending() {
    // ViewModel for fetching crypto data
    class CryptoViewModalT : ViewModel() {
        private val _trendingcoins = MutableLiveData<List<CryptoCoin>>()
        val trendingCoins: LiveData<List<CryptoCoin>> = _trendingcoins

        fun fetchCryptoData() {
            viewModelScope.launch {
                try {
                    val response = RetrofitInstance.api.getCryptoCoins()
                    categorizeCoins(response)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        private fun categorizeCoins(coins: List<CryptoCoin>) {
            _trendingcoins.postValue(coins)
        }
    }

    val viewModel = CryptoViewModalT()
    viewModel.fetchCryptoData()

    val trendingCoins by viewModel.trendingCoins.observeAsState(emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
    ) {
        // Add header as a separate item
        item {
            Text(
                text = "Trending 📊",
                modifier = Modifier.padding(16.dp),
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        // Items for the list
        items(trendingCoins) { coin ->
            CryptoCoinItemT(coin)
        }

        // Handle additional states for loading or error
        if (trendingCoins.isEmpty()) {
            item {
                Text(text = "Loading...", modifier = Modifier.padding(16.dp), color = Color.White)
            }
        }
    }
}

@Composable
fun CryptoCoinItemT(coin: CryptoCoin) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(coin.image),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(shape = CircleShape)
                    .border(
                        border = BorderStroke(width = 1.dp, color = Color.Black),
                        shape = CircleShape
                    ),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )


            Spacer(modifier = Modifier.width(30.dp))
            Column(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .width(100.dp)
                    .align(alignment = Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                //coin name ........................
                Text(
                    text = coin.name,
                    color = Color.Blue,
                    style = TextStyle(fontSize = 18.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp, end = 5.dp, start = 5.dp)
                )

                //coin price.................................
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${coin.current_price} USD",
                    color = Color.Red,
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 5.dp, end = 5.dp, start = 5.dp)
                )
            }

            //buy button ....................
            Spacer(modifier = Modifier.width(20.dp))
            OutlinedButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxWidth()
                    .background(color = Color.Green, shape = RoundedCornerShape(10.dp))
                    .height(50.dp)
                    .align(Alignment.CenterVertically),
                border = BorderStroke(1.dp, color = Color.Green)
            ) {
                Text(
                    text = "Buy",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    style = TextStyle(textMotion = TextMotion.Animated)
                )
            }


            //sell button ....................
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color.Red, shape = RoundedCornerShape(10.dp))
                    .align(Alignment.CenterVertically),
                border = BorderStroke(1.dp, color = Color.Red)
            ) {
                Text(
                    text = "Sell",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White,
                    style = TextStyle(textMotion = TextMotion.Animated)
                )
            }

        }
    }
}



// Gainers coins data in to tab with pager............
@Composable
fun Gainers() {
    // ViewModel for fetching crypto data
    class CryptoViewModalG : ViewModel() {
        private val _gainerCoins = MutableLiveData<List<CryptoCoin>>()
        val gainerCoins: LiveData<List<CryptoCoin>> = _gainerCoins

        fun fetchCryptoData() {
            viewModelScope.launch {
                try {
                    val response = RetrofitInstance.api.getCryptoCoins()
                    categorizeCoins(response)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        private fun categorizeCoins(coins: List<CryptoCoin>) {
            _gainerCoins.postValue(coins.sortedByDescending { it.current_price }.take(150)) // Top 10 Gainers
        }
    }

    val viewModel = CryptoViewModalG()
    viewModel.fetchCryptoData()

    val gainerCoins by viewModel.gainerCoins.observeAsState(emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
    ) {
        // Add header as a separate item
        item {
            Text(
                text = "Top Gainers 🚀",
                modifier = Modifier.padding(16.dp),
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        // Items for the list
        items(gainerCoins) { coin ->
            CryptoCoinItemG(coin)
        }

        // Handle additional states for loading or error
        if (gainerCoins.isEmpty()) {
            item {
                Text(text = "Loading...", modifier = Modifier.padding(16.dp), color = Color.White)
            }
        }
    }
}

@Composable
fun CryptoCoinItemG(coin: CryptoCoin) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(coin.image),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(shape = CircleShape)
                    .border(
                        border = BorderStroke(width = 1.dp, color = Color.Black),
                        shape = CircleShape
                    ),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )


            Spacer(modifier = Modifier.width(30.dp))
            Column(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .width(100.dp)
                    .align(alignment = Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                //coin name ........................
                Text(
                    text = coin.name,
                    color = Color.Blue,
                    style = TextStyle(fontSize = 18.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp, end = 5.dp, start = 5.dp)
                )

                //coin price.................................
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${coin.current_price} USD",
                    color = Color.Red,
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 5.dp, end = 5.dp, start = 5.dp)
                )
            }

            //buy button ....................
            Spacer(modifier = Modifier.width(20.dp))
            OutlinedButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxWidth()
                    .background(color = Color.Green, shape = RoundedCornerShape(10.dp))
                    .height(50.dp)
                    .align(Alignment.CenterVertically),
                border = BorderStroke(1.dp, color = Color.Green)
            ) {
                Text(
                    text = "Buy",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    style = TextStyle(textMotion = TextMotion.Animated)
                )
            }


            //sell button ....................
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color.Red, shape = RoundedCornerShape(10.dp))
                    .align(Alignment.CenterVertically),
                border = BorderStroke(1.dp, color = Color.Red)
            ) {
               Text(
                    text = "Sell",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White,
                    style = TextStyle(textMotion = TextMotion.Animated)
                )
            }

        }
    }
}


// Losers coins data in to tab with pager............
@Composable
fun Losers() {
    // ViewModel for fetching crypto data
    class CryptoViewModalL : ViewModel() {
        private val _losersCoins = MutableLiveData<List<CryptoCoin>>()
        val losersCoins: LiveData<List<CryptoCoin>> = _losersCoins

        fun fetchCryptoData() {
            viewModelScope.launch {
                try {
                    val response = RetrofitInstance.api.getCryptoCoins()
                    categorizeCoins(response)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        private fun categorizeCoins(coins: List<CryptoCoin>) {

            _losersCoins.postValue(coins.sortedBy { it.current_price }.take(150))// Top 10 losers
            /*val sortedLosers = coins
                .filter { it.current_price > 0 && it.previous_price!! > 0 } // Ensure prices are valid
                .sortedBy { ((it.current_price - it.previous_price!!) / it.previous_price) * 100 } // Calculate percentage change
                .take(50)
            _losersCoins.postValue(sortedLosers)*/
        }
    }

    val viewModel = CryptoViewModalL()
    viewModel.fetchCryptoData()

    val losersCoins by viewModel.losersCoins.observeAsState(emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
    ) {
        // Add header as a separate item
        item {
            Text(
                text = "Top Losers 📉",
                modifier = Modifier.padding(16.dp),
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        // Items for the list
        items(losersCoins) { coin ->
            CryptoCoinItemL(coin)
        }

        // Handle additional states for loading or error
        if (losersCoins.isEmpty()) {
            item {
                Text(text = "Loading...", modifier = Modifier.padding(16.dp), color = Color.White)
            }
        }
    }
}

@Composable
fun CryptoCoinItemL(coin: CryptoCoin) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(coin.image),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(shape = CircleShape)
                    .border(
                        border = BorderStroke(width = 1.dp, color = Color.Black),
                        shape = CircleShape
                    ),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )


            Spacer(modifier = Modifier.width(30.dp))
            Column(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .width(100.dp)
                    .align(alignment = Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                //coin name ........................
                Text(
                    text = coin.name,
                    color = Color.Blue,
                    style = TextStyle(fontSize = 18.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp, end = 5.dp, start = 5.dp)
                )

                //coin price.................................
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${coin.current_price} USD",
                    color = Color.Red,
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 5.dp, end = 5.dp, start = 5.dp)
                )
            }

            //buy button ....................
            Spacer(modifier = Modifier.width(20.dp))
            OutlinedButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxWidth()
                    .background(color = Color.Green, shape = RoundedCornerShape(10.dp))
                    .height(50.dp)
                    .align(Alignment.CenterVertically),
                border = BorderStroke(1.dp, color = Color.Green)
            ) {
                Text(
                    text = "Buy",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    style = TextStyle(textMotion = TextMotion.Animated)
                )
            }


            //sell button ....................
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color.Red, shape = RoundedCornerShape(10.dp))
                    .align(Alignment.CenterVertically),
                border = BorderStroke(1.dp, color = Color.Red)
            ) {
                Text(
                    text = "Sell",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White,
                    style = TextStyle(textMotion = TextMotion.Animated)
                )
            }

        }
    }
}