package com.app.kaushalprajapati.myapplication

import android.content.Context
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    val context = LocalContext.current

    // infinite the transition of animation
    val infiniteTransition = rememberInfiniteTransition()

    // animating the scale of text
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Moving animation for "Future is here"
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    //animated gradient background
    val colors = listOf(
        Color(0xFFF50303), // Deep RED
        Color(0xFFFFFFFF), // Deep White
        Color(0xFF01FA68)  // Deep Green
    )

    val animatedBrush = Brush.verticalGradient(colors)


    Box(
        modifier = Modifier.fillMaxSize()
            .background(brush = animatedBrush),
        contentAlignment = Alignment.Center){
        Text(text = "Crypto",
            color = Color.Green,
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Text(text = "future is here",
            color = Color.Red,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 3.sp,
            modifier = Modifier.padding(top = 80.dp).offset(y = offsetY.dp)
        )


    }

    LaunchedEffect(Unit) {
        delay(3000)
        val isloggedIn = checkIfLoggedIn(context)
        if (isloggedIn){
            navController.navigate("main_screen"){
                popUpTo("splash_screen"){ inclusive = true }
            }
        } else{
            navController.navigate("sign_in"){
                popUpTo("splash_screen"){inclusive = true}
            }
        }
    }
}

fun checkIfLoggedIn(context: Context): Boolean {
    val sharedPref = context.getSharedPreferences("user_prefs",Context.MODE_PRIVATE)
    return sharedPref.getBoolean("isloggedIn",false)
}

@Suppress("VisualLintAtfColorblindCheck", "VisualLintAccessibilityTestFramework")
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    // Preview requires a mock NavController
    SplashScreen(navController = androidx.navigation.compose.rememberNavController())
}


