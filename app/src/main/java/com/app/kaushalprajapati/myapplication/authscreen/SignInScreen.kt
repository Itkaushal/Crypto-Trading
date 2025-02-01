package com.app.kaushalprajapati.myapplication.authscreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
@Composable
fun SignInScreen(navController : NavController){
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Sign In", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (validateUser(context, email, password)) {
                    setLoggedIn(context,true)
                    navController.navigate("main_screen"){
                        popUpTo("sign_in") {inclusive = true}
                    }
                } else {
                    Toast.makeText(context, "invalid email/password?$email\n $password", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sign In")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(onClick = {
            navController.navigate("sign_up")
            Toast.makeText(context, "Redirecting...sign_up page", Toast.LENGTH_SHORT).show()
         },
            modifier = Modifier
                .padding(10.dp)
                .width(150.dp)
                .background(color = Color.Blue, shape = RoundedCornerShape(20.dp))
        ) {
            Text(text = "SignOut")
        }
    }
}

fun setLoggedIn(context: Context, isLoggedIn: Boolean) {
    val sharedpref = context.getSharedPreferences("user_prefs",Context.MODE_PRIVATE)
    with(sharedpref.edit()){
        putBoolean("isloggedIn",isLoggedIn)
        apply()
    }
}

fun validateUser(context: Context, email: String, password: String): Boolean {
    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val savedEmail = sharedPref.getString("email", null)
    val savedPassword = sharedPref.getString("password", null)
    return email == savedEmail && password == savedPassword
}