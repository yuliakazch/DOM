package com.dom.features.home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dom.core.NavigationKeys
import com.dom.features.home.presentation.HomeEffect
import com.dom.features.home.presentation.HomeViewModel

@ExperimentalFoundationApi
@Composable
fun HomeDestination(navController: NavHostController) {
	val viewModel = hiltViewModel<HomeViewModel>()
	val state = viewModel.viewState.collectAsState().value
	HomeScreen(
		state = state,
		effectFlow = viewModel.effect,
		onEventSent = { event -> viewModel.setEvent(event) },
		onNavigationRequested = { navigationEffect ->
			when (navigationEffect) {
				is HomeEffect.Navigation.ToFolderDetail -> {
					navController.navigate("${NavigationKeys.FOLDER_DETAIL}/${navigationEffect.folderId}")
				}
			}
		}
	)
}