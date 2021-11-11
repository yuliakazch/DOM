package com.dom.features.splash.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dom.features.splash.presentation.SplashEffect
import com.dom.features.splash.presentation.SplashViewModel
import com.dom.core.NavigationKeys

@Composable
fun SplashDestination(navController: NavHostController) {
	val viewModel: SplashViewModel = hiltViewModel()
	SplashScreen(
		effectFlow = viewModel.effect,
		onNavigationRequested = { navigationEffect ->
			when (navigationEffect) {
				is SplashEffect.Navigation.ToAuthorization -> {
					navController.navigate(NavigationKeys.SIGN_IN) {
						popUpTo(NavigationKeys.SPLASH) { inclusive = true }
					}
				}

				is SplashEffect.Navigation.ToHome          -> {
					navController.navigate(NavigationKeys.HOME) {
						popUpTo(NavigationKeys.SPLASH) { inclusive = true }
					}
				}
			}
		}
	)
}