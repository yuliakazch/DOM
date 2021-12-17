package com.dom.features.profile.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dom.R
import com.dom.components.error.ErrorView
import com.dom.features.profile.presentation.ProfileEffect
import com.dom.features.profile.presentation.ProfileEvent
import com.dom.features.profile.presentation.ProfileState
import com.dom.components.progress.LoadingView
import com.dom.components.text.TwoTextView
import com.dom.components.theme.VeryLightGray
import com.dom.components.topbar.TopBarEndIconView
import com.dom.core.LAUNCH_LISTEN_FOR_EFFECTS
import com.dom.shared.user.domain.entity.Profile
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
        topBar = {
            TopBarEndIconView(
                title = stringResource(R.string.profile_title),
                icon = Icons.Filled.ExitToApp,
                onIconClicked = { onEventSent(ProfileEvent.LogoutClicked) },
            )
        },
    ) {
        Surface(
            color = VeryLightGray,
            modifier = Modifier.fillMaxSize()
        ) {
            when {
                state.loading -> LoadingView()

                state.data == null -> ErrorView()

                else -> ProfileView(state.data, onEventSent)

            }
        }
    }
}

@Composable
fun ProfileView(
    profile: Profile,
    onEventSent: (event: ProfileEvent) -> Unit,
) {
    Column {
        TwoTextView(
            textOne = stringResource(R.string.login),
            textTwo = profile.username,
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
        ) {
            Button(
                onClick = { onEventSent(ProfileEvent.DeleteClicked) },
            ) {
                Text(stringResource(R.string.profile_delete))
            }
        }
    }
}