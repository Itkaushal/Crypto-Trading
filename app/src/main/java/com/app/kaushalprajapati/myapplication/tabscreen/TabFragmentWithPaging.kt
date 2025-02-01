package com.app.kaushalprajapati.myapplication.tabscreen
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Indicator

@Composable
fun TabFragmentWithPaging(){
    val tabs = listOf("Trending","Gainers","Losers")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column {
      TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .fillMaxWidth(),
          indicator = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title, style = TextStyle(color = Color.Gray, fontSize = 18.sp, fontWeight = FontWeight.Bold)) },
                   modifier = Modifier
                       .background(color = Color.Black)
                       .padding(5.dp)
                       .border(width = 1.dp, color = Color.Magenta.copy(alpha = 0.2f), shape = RoundedCornerShape(30.dp)),
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Black,
                )
            }
        }

        when (selectedTabIndex) {
            0 -> Trending()
            1 -> Gainers()
            2 -> Losers()
        }
    }

}