package com.app.kaushalprajapati.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.kaushalprajapati.myapplication.authscreen.SignInScreen
import com.app.kaushalprajapati.myapplication.authscreen.SignUpScreen
import com.app.kaushalprajapati.myapplication.mainscreens.MainScreen
import com.app.kaushalprajapati.myapplication.ui.theme.CryptoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ){
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "splash_screen"){
                        composable("splash_screen"){ SplashScreen(navController)}
                        composable("sign_up"){SignUpScreen(navController)}
                        composable("sign_in"){SignInScreen(navController)}
                        composable("main_screen"){ MainScreen()}
                    }
                }
            }
        }
    }
}
