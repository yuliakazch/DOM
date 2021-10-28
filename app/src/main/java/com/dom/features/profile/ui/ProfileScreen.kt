package com.dom.features.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dom.R
import com.dom.features.profile.presentation.ProfileEffect
import com.dom.features.profile.presentation.ProfileEvent
import com.dom.features.profile.presentation.ProfileState
import com.dom.shared.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.dom.shared.ui.progress.LoadingView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun ProfileScreen(
    state: ProfileState,
    effectFlow: Flow<ProfileEffect>?,
    onEventSent: (event: ProfileEvent) -> Unit,
    onNavigationRequested: (navigationEffect: ProfileEffect.Navigation) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val textError = stringResource(R.string.error)

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is ProfileEffect.Error ->
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = effect.message ?: textError,
                        duration = SnackbarDuration.Short
                    )
                is ProfileEffect.Navigation ->
                    onNavigationRequested(effect)
            }
        }?.collect()
    }

    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        if (state.loading) {
            LoadingView()
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(stringResource(R.string.profile_title))
                Button(
                    onClick = { onEventSent(ProfileEvent.LogoutClicked) },
                    modifier = Modifier.padding(vertical = 24.dp),
                ) {
                    Text(stringResource(R.string.profile_logout))
                }
            }
        }
    }
}