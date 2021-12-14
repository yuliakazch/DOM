package com.dom.features.subject.edit.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dom.core.NavigationKeys
import com.dom.features.subject.edit.presentation.SubjectEditEffect
import com.dom.features.subject.edit.presentation.SubjectEditViewModel

@Composable
fun SubjectEditDestination(navController: NavHostController) {
    val viewModel = hiltViewModel<SubjectEditViewModel>()
    val state = viewModel.viewState.collectAsState().value

    SubjectEditScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is SubjectEditEffect.Navigation.ToBack -> {
                    navController.popBackStack()
                }

                is SubjectEditEffect.Navigation.ToSubjectDetail -> {
                    navController.navigate(NavigationKeys.HOME) {
                        popUpTo(NavigationKeys.HOME) { inclusive = true }
                    }
                }
            }
        }
    )
}