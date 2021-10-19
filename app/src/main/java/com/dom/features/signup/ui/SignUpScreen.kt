package com.dom.features.signup.ui

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
import com.dom.features.signup.presentation.SignUpEffect
import com.dom.features.signup.presentation.SignUpEvent
import com.dom.features.signup.presentation.SignUpState
import com.dom.shared.core.LAUNCH_LISTEN_FOR_EFFECTS
import com.dom.shared.ui.image.LogoView
import com.dom.shared.ui.progress.LoadingView
import com.dom.shared.ui.textfield.LoginView
import com.dom.shared.ui.textfield.PasswordDoneView
import com.dom.shared.ui.textfield.PasswordNextView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@ExperimentalComposeUiApi
@Composable
fun SignUpScreen(
    state: SignUpState,
    effectFlow: Flow<SignUpEffect>?,
    onEventSent: (event: SignUpEvent) -> Unit,
    onNavigationRequested: (navigationEffect: SignUpEffect.Navigation) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val textError = stringResource(R.string.error)

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is SignUpEffect.Error ->
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = effect.message ?: textError,
                        duration = SnackbarDuration.Short
                    )
                is SignUpEffect.Navigation ->
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
            SignUpContentView(state, onEventSent)
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun SignUpContentView(
    state: SignUpState,
    onEventSent: (event: SignUpEvent) -> Unit,
) {
    val focusRequesterOne = remember { FocusRequester() }
    val focusRequesterTwo = remember { FocusRequester() }
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
            login = state.content.login,
            focusRequester = focusRequesterOne,
            onLoginChange = { onEventSent(SignUpEvent.LoginChanged(it)) },
        )
        PasswordNextView(
            password = state.content.password,
            label = stringResource(R.string.password),
            focusRequesterOne = focusRequesterOne,
            focusRequesterTwo = focusRequesterTwo,
            onPasswordChange = { onEventSent(SignUpEvent.PasswordChanged(it)) }
        )
        PasswordDoneView(
            password = state.content.passwordAgain,
            label = stringResource(R.string.password_again),
            keyboardController = keyboardController,
            focusRequester = focusRequesterTwo,
            onPasswordChange = { onEventSent(SignUpEvent.PasswordAgainChanged(it)) },
        )
        Button(
            onClick = {
                onEventSent(SignUpEvent.SignUpClicked)
            },
        ) {
            Text(stringResource(R.string.sign_up))
        }
        Text(
            text = stringResource(R.string.already_have_account),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                onEventSent(SignUpEvent.AuthorizationClicked)
            },
        )
    }
}