package com.dom.features.signin.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dom.R
import com.dom.features.signin.presentation.SignInViewModel
import com.dom.shared.ui.image.LogoView
import com.dom.shared.ui.textfield.LoginView
import com.dom.shared.ui.textfield.PasswordDoneView
import com.dom.shared.util.NavigationKeys.HOME
import com.dom.shared.util.NavigationKeys.SIGN_UP

@ExperimentalComposeUiApi
@Composable
fun SignInView(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        LogoView()
        LoginView(
            login = login,
            focusRequester = focusRequester,
        ) {
            login = it
        }
        PasswordDoneView(
            password = password,
            label = stringResource(R.string.password),
            keyboardController = keyboardController,
            focusRequester = focusRequester,
        ) {
            password = it
        }
        Button(
            onClick = {
                viewModel.login(login, password)
                navController.navigate(HOME)
            }
        ) {
            Text(stringResource(R.string.sign_in))
        }
        Text(
            text = stringResource(R.string.registration),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable(onClick = { navController.navigate(SIGN_UP) }),
        )
    }
}