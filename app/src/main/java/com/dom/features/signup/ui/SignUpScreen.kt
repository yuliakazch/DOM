package com.dom.features.signup.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.dom.R
import com.dom.features.signup.presentation.SignUpEffect
import com.dom.features.signup.presentation.SignUpEvent
import com.dom.features.signup.presentation.SignUpState
import com.dom.shared.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.dom.shared.ui.image.LogoView
import com.dom.shared.ui.progress.LoadingView
import com.dom.shared.ui.textfield.LoginView
import com.dom.shared.ui.textfield.PasswordDoneView
import com.dom.shared.ui.textfield.PasswordNextView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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
        if (state.loading) {
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

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        item { LogoView() }
        item {
            LoginView(
                login = state.content.login,
                focusRequester = focusRequesterOne,
                onAnimateScrolled = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(index = 2)
                    }
                },
                onLoginChange = { onEventSent(SignUpEvent.LoginChanged(it)) },
            )
        }
        item {
            PasswordNextView(
                password = state.content.password,
                label = stringResource(R.string.password),
                focusRequesterOne = focusRequesterOne,
                focusRequesterTwo = focusRequesterTwo,
                onAnimateScrolled = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(index = 3)
                    }
                },
                onPasswordChange = { onEventSent(SignUpEvent.PasswordChanged(it)) }
            )
        }
        item {
            PasswordDoneView(
                password = state.content.passwordAgain,
                label = stringResource(R.string.password_again),
                keyboardController = keyboardController,
                focusRequester = focusRequesterTwo,
                onPasswordChange = { onEventSent(SignUpEvent.PasswordAgainChanged(it)) },
            )
        }
        item {
            Button(
                onClick = { onEventSent(SignUpEvent.SignUpClicked) },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(stringResource(R.string.sign_up))
            }
        }
        item {
            Text(
                text = stringResource(R.string.already_have_account),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    onEventSent(SignUpEvent.AuthorizationClicked)
                },
            )
        }
    }
}