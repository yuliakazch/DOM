package com.dom.features.signup.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dom.features.signup.presentation.SignUpEffect
import com.dom.features.signup.presentation.SignUpViewModel

@ExperimentalComposeUiApi
@Composable
fun SignUpDestination(navController: NavHostController) {
    val viewModel: SignUpViewModel = hiltViewModel()
    val state = viewModel.viewState.collectAsState().value
    SignUpScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is SignUpEffect.Navigation.ToAuthorization -> {
                    navController.popBackStack()
                }
            }
        }
    )
}