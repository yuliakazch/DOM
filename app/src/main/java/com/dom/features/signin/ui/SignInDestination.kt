package com.dom.features.signin.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dom.features.signin.presentation.SignInEffect
import com.dom.features.signin.presentation.SignInViewModel
import com.dom.core.NavigationKeys

@ExperimentalComposeUiApi
@Composable
fun SignInDestination(navController: NavHostController) {
	val viewModel: SignInViewModel = hiltViewModel()
	val state = viewModel.viewState.collectAsState().value
	SignInScreen(
		state = state,
		effectFlow = viewModel.effect,
		onEventSent = { event -> viewModel.setEvent(event) },
		onNavigationRequested = { navigationEffect ->
			when (navigationEffect) {
				is SignInEffect.Navigation.ToHome -> {
					navController.navigate(NavigationKeys.HOME) {
						popUpTo(NavigationKeys.SIGN_IN) { inclusive = true }
					}
				}

				is SignInEffect.Navigation.ToRegistration -> navController.navigate(NavigationKeys.SIGN_UP)
			}
		}
	)
}