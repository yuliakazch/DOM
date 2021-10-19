package com.dom.features.signin.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import com.dom.R
import com.dom.features.signin.presentation.SignInEffect
import com.dom.features.signin.presentation.SignInEvent
import com.dom.features.signin.presentation.SignInState
import com.dom.shared.core.LAUNCH_LISTEN_FOR_EFFECTS
import com.dom.shared.ui.image.LogoView
import com.dom.shared.ui.progress.LoadingView
import com.dom.shared.ui.textfield.LoginView
import com.dom.shared.ui.textfield.PasswordDoneView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@ExperimentalComposeUiApi
@Composable
fun SignInScreen(
    state: SignInState,
    effectFlow: Flow<SignInEffect>?,
    onEventSent: (event: SignInEvent) -> Unit,
    onNavigationRequested: (navigationEffect: SignInEffect.Navigation) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val textError = stringResource(R.string.error)

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is SignInEffect.Error ->
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = effect.message ?: textError,
                        duration = SnackbarDuration.Short
                    )
                is SignInEffect.Navigation ->
                    onNavigationRequested(effect)
            }
        }?.collect()
    }

    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        if (state.isLoading) {
            LoadingView()
        } else {
            SignInContentView(state, onEventSent)
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun SignInContentView(
    state: SignInState,
    onEventSent: (event: SignInEvent) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
    ) {
        LogoView()
        LoginView(
            login = state.credentials.login,
            focusRequester = focusRequester,
            onLoginChange = { onEventSent(SignInEvent.LoginChanged(it)) },
        )
        PasswordDoneView(
            password = state.credentials.password,
            label = stringResource(R.string.password),
            keyboardController = keyboardController,
            focusRequester = focusRequester,
            onPasswordChange = { onEventSent(SignInEvent.PasswordChanged(it)) },
        )
        Button(
            onClick = { onEventSent(SignInEvent.SignInClicked) },
        ) {
            Text(stringResource(R.string.sign_in))
        }
        Text(
            text = stringResource(R.string.registration),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                onEventSent(SignInEvent.RegistrationClicked)
            },
        )
    }
}