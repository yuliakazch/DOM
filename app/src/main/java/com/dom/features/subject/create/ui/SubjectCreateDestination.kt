package com.dom.features.subject.create.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dom.core.NavigationKeys
import com.dom.features.subject.create.presentation.SubjectCreateEffect
import com.dom.features.subject.create.presentation.SubjectCreateViewModel

@Composable
fun SubjectCreateDestination(navController: NavHostController) {
    val viewModel = hiltViewModel<SubjectCreateViewModel>()
    val state = viewModel.viewState.collectAsState().value

    SubjectCreateScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is SubjectCreateEffect.Navigation.ToBack -> {
                    navController.popBackStack()
                }

                is SubjectCreateEffect.Navigation.ToHome -> {
                    navController.navigate(NavigationKeys.HOME) {
                        popUpTo(NavigationKeys.HOME) { inclusive = true }
                    }
                }
            }
        }
    )
}