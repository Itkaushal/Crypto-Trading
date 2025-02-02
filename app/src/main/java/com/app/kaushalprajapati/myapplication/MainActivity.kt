package com.app.kaushalprajapati.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.kaushalprajapati.myapplication.authscreen.SignInScreen
import com.app.kaushalprajapati.myapplication.authscreen.SignUpScreen
import com.app.kaushalprajapati.myapplication.cardScreens.InstantBuyScreen
import com.app.kaushalprajapati.myapplication.mainscreens.HomeScreen
import com.app.kaushalprajapati.myapplication.mainscreens.MainScreen
import com.app.kaushalprajapati.myapplication.ui.theme.CryptoTheme
import com.app.kaushalprajapati.myapplication.utils.isInternetAvailable

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

                    val context = LocalContext.current

                    LaunchedEffect(Unit) {
                        if (!isInternetAvailable(context)) {
                            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show()
                        } else{
                            Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show()
                        }
                    }

                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "splash_screen"){
                        composable("splash_screen"){ SplashScreen(navController)}
                        composable("sign_up"){SignUpScreen(navController)}
                        composable("sign_in"){SignInScreen(navController)}
                        composable("main_screen"){ MainScreen(navController)}
                        composable("home_screen"){ HomeScreen(navController) }
                    }
                }
            }
        }
    }
}
