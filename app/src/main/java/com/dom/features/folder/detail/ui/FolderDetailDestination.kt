package com.dom.features.folder.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dom.core.NavigationKeys.HOME
import com.dom.core.NavigationKeys.SUBJECT_CREATE
import com.dom.core.NavigationKeys.SUBJECT_DETAIL
import com.dom.features.folder.detail.presentation.FolderDetailEffect
import com.dom.features.folder.detail.presentation.FolderDetailViewModel

@Composable
fun FolderDetailDestination(navController: NavHostController) {
    val viewModel = hiltViewModel<FolderDetailViewModel>()
    val state = viewModel.viewState.collectAsState().value

    FolderDetailScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is FolderDetailEffect.Navigation.ToBack -> {
                    navController.popBackStack()
                }

                is FolderDetailEffect.Navigation.ToHome -> {
                    navController.navigate(HOME) {
                        popUpTo(HOME) { inclusive = true }
                    }
                }

                is FolderDetailEffect.Navigation.ToCreateSubject -> {
                    navController.navigate(SUBJECT_CREATE)
                }

                is FolderDetailEffect.Navigation.ToSubjectDetail -> {
                    navController.navigate("${SUBJECT_DETAIL}/${navigationEffect.subjectId}")
                }
            }
        }
    )
}