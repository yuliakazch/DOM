package com.dom.features.signup.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController

@Composable
fun SignUpView(navController: NavController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordAgain by remember { mutableStateOf("") }

    Column {
        TextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("логин") },
            singleLine = true,
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("пароль") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        TextField(
            value = passwordAgain,
            onValueChange = { passwordAgain = it },
            label = { Text("еще раз пароль") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(
            onClick = { navController.popBackStack() }
        ) {
            Text("назад")
        }
        Button(
            onClick = { navController.navigate("signIn") }
        ) {
            Text("ок")
        }
    }
}