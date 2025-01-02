package com.app.kaushalprajapati.myapplication.cryptoApi

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
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
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
import coil.compose.rememberAsyncImagePainter

@Composable
fun CryptoCoinItem(coin: CryptoCoin){

    Card (
      modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp)
          .background(color = Color.Black),
        shape = RoundedCornerShape(10),
        border = BorderStroke(1.dp, Color.Black),
    ){

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(coin.image),
                contentDescription ="Image",
                modifier = Modifier
                    .size(70.dp)
                    .background(color = Color.White, shape = CircleShape)
                    .clip(shape = CircleShape)
                    .border(
                        border = BorderStroke(width = 1.dp, color = Color.White),
                    ),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(10.dp))
            Column(
                Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .width(100.dp)
                    .align(alignment = Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // coin name .............................
                Text(text = coin.name,
                    color = Color.Blue,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 16.sp, letterSpacing = 1.sp),
                    modifier = Modifier
                        .padding(top = 5.dp, end = 5.dp, start = 5.dp))

                // coin price.................................
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "${coin.current_price} USD",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 16.sp,letterSpacing = 1.sp),
                    modifier = Modifier
                        .padding(bottom = 5.dp, end = 5.dp, start = 5.dp))

            }

            //buy button ....................
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxWidth()
                    .background(color = Color.Green, shape = RoundedCornerShape(10.dp))
                    .height(50.dp)
                    .align(Alignment.CenterVertically),
                border = BorderStroke(1.dp, color = Color.Green)
            ) {
                Text(text = "Buy",
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
                border = BorderStroke(1.dp, color = Color.Red,)
            ) {
                Text(text = "Sell",
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




@Composable
fun CryptoList(viewModal: CryptoViewModal){

    val cryptoList = viewModal.cryptoList.observeAsState(initial = emptyList())
    LazyColumn {
        items(cryptoList.value){
            coin-> CryptoCoinItem(coin)

        }
    }
}

//display data screen..........
@Composable
fun CryptoScreen(viewModal: CryptoViewModal = CryptoViewModal()){
    viewModal.fetchCryptoData()
    CryptoList(viewModal)
}

