package com.dom.features.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dom.features.profile.presentation.ProfileEffect
import com.dom.features.profile.presentation.ProfileViewModel
import com.dom.core.NavigationKeys

@Composable
fun ProfileDestination(navController: NavHostController) {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val state = viewModel.viewState.collectAsState().value

    ProfileScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is ProfileEffect.Navigation.ToSignIn -> {
                    navController.navigate(NavigationKeys.SIGN_IN) {
                        popUpTo(NavigationKeys.HOME_ROUTE) { inclusive = true }
                    }
                }
            }
        }
    )
}