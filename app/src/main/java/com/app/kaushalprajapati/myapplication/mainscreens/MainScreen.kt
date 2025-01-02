package com.app.kaushalprajapati.myapplication.mainscreens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.kaushalprajapati.myapplication.navscreen.BottomNavigationBar
import com.app.kaushalprajapati.myapplication.navscreen.NavigationGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationBar(navController) }, backgroundColor = Color.Black) {
        NavigationGraph(navController = navController)
    }
}

@Preview
@Composable
fun MainScreenPreview(){
    MainScreen()
}