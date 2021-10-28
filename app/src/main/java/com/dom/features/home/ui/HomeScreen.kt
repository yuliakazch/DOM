package com.dom.features.home.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dom.R
import com.dom.features.home.presentation.HomeEffect
import com.dom.features.home.presentation.HomeEvent
import com.dom.features.home.presentation.HomeState
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    state: HomeState,
    effectFlow: Flow<HomeEffect>?,
    onEventSent: (event: HomeEvent) -> Unit,
    onNavigationRequested: (navigationEffect: HomeEffect.Navigation) -> Unit
) {
    Text(stringResource(R.string.home_title))
}