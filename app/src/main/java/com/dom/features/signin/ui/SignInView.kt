package com.dom.features.signin.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun SignInView() {
    var login by remember { mutableStateOf("") }
    var passsword by remember { mutableStateOf("") }

    Column {
        TextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("логин") },
            singleLine = true,
        )
        TextField(
            value = passsword,
            onValueChange = { passsword = it },
            label = { Text("пароль") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(
            onClick = { /* TODO: переход на главную */ }
        ) {
            Text("вход")
        }
        Text(
            text = "регистрация",
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { /* TODO: переход на регистрацию */ }
        )
    }
}