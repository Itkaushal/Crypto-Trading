package com.app.kaushalprajapati.myapplication.navscreen

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.app.kaushalprajapati.myapplication.R

sealed class BottomNavDataModal(val title: String, val icon: ImageVector, val route: String){
    object Home : BottomNavDataModal("Home", Icons.Default.Home,"home")
    object Market : BottomNavDataModal("Market", Icons.Default.ShoppingCart,"market")
    object Portfolio : BottomNavDataModal("Portfolio",Icons.Default.AccountCircle,"portfolio")

}