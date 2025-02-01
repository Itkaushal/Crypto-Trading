@file:Suppress("NAME_SHADOWING")

package com.app.kaushalprajapati.myapplication.mainscreens

import android.annotation.SuppressLint
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.kaushalprajapati.myapplication.navscreen.BottomNavigationBar
import com.app.kaushalprajapati.myapplication.navscreen.NavigationGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationBar(navController) }, backgroundColor = Color.Black) {
        NavigationGraph(navController = navController)
    }
}
