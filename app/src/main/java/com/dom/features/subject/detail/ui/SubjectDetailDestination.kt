package com.dom.features.subject.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dom.core.NavigationKeys.HOME
import com.dom.features.subject.detail.presentation.SubjectDetailEffect
import com.dom.features.subject.detail.presentation.SubjectDetailViewModel

@Composable
fun SubjectDetailDestination(navController: NavHostController) {
    val viewModel = hiltViewModel<SubjectDetailViewModel>()
    val state = viewModel.viewState.collectAsState().value

    SubjectDetailScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is SubjectDetailEffect.Navigation.ToBack -> {
                    navController.popBackStack()
                }

                is SubjectDetailEffect.Navigation.ToHome -> {
                    navController.navigate(HOME) {
                        popUpTo(HOME) { inclusive = true }
                    }
                }

                is SubjectDetailEffect.Navigation.ToEditSubject -> {
                    //navController.navigate("${NavigationKeys.SUBJECT_EDIT}/${navigationEffect.subjectId}")
                }
            }
        }
    )
}