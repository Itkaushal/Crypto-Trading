package com.app.kaushalprajapati.myapplication.tabscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TabFragmentWithPaging(){
    val tabs = listOf("Trending","Gainers","Losers")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = Color.Blue,
            containerColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title) },
                   modifier = Modifier.background(color = Color.White).padding(5.dp),
                    selectedContentColor = Color.Blue,
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