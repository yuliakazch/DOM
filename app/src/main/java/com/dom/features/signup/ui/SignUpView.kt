package com.dom.features.signup.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import com.dom.R
import com.dom.shared.ui.image.LogoView
import com.dom.shared.ui.textfield.LoginView
import com.dom.shared.ui.textfield.PasswordDoneView
import com.dom.shared.ui.textfield.PasswordNextView

@ExperimentalComposeUiApi
@Composable
fun SignUpView(navController: NavController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordAgain by remember { mutableStateOf("") }
    val focusRequesterOne = remember { FocusRequester() }
    val focusRequesterTwo = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LogoView()
        LoginView(login, focusRequesterOne) {
            login = it
        }
        PasswordNextView(
            password = password,
            label = stringResource(R.string.password),
            focusRequesterOne = focusRequesterOne,
            focusRequesterTwo = focusRequesterTwo,
        ) {
            password = it
        }
        PasswordDoneView(
            password = passwordAgain,
            label = stringResource(R.string.password_again),
            keyboardController = keyboardController,
            focusRequester = focusRequesterTwo,
        ) {
            passwordAgain = it
        }
        Button(
            onClick = { navController.popBackStack() },
        ) {
            Text(stringResource(R.string.sign_up))
        }
        Text(
            text = stringResource(R.string.already_have_account),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable(onClick = { navController.popBackStack() }),
        )
    }
}