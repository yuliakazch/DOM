package com.dom.features.folder.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
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
            }
        }
    )
}