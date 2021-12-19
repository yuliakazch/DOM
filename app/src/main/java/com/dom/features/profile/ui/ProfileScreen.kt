package com.dom.features.profile.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dom.R
import com.dom.components.error.ErrorView
import com.dom.features.profile.presentation.ProfileEffect
import com.dom.features.profile.presentation.ProfileEvent
import com.dom.features.profile.presentation.ProfileState
import com.dom.components.progress.LoadingView
import com.dom.components.text.TwoTextView
import com.dom.components.textfield.EditTextView
import com.dom.components.theme.VeryLightGray
import com.dom.components.topbar.TopBarEndIconView
import com.dom.core.LAUNCH_LISTEN_FOR_EFFECTS
import com.dom.features.profile.presentation.PasswordContent
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

                state.profile == null -> ErrorView()

                else -> ProfileView(state.profile, state.passwords, onEventSent)

            }
        }
    }
}

@Composable
fun ProfileView(
    profile: Profile,
    passwords: PasswordContent,
    onEventSent: (event: ProfileEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(bottom = 64.dp)
    ) {
        item {
            TwoTextView(
                textOne = stringResource(R.string.login),
                textTwo = profile.username,
            )
        }

        item {
            ChangePasswordView(passwords, onEventSent)
        }

        item {
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
}

@Composable
fun ChangePasswordView(
    passwords: PasswordContent,
    onEventSent: (event: ProfileEvent) -> Unit,
) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(R.string.change_password),
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            EditTextView(
                text = passwords.currentPassword,
                label = stringResource(R.string.current_password),
                onTextChange = { onEventSent(ProfileEvent.CurrentPasswordChanged(it)) },
                imeAction = ImeAction.Next,
                visualTransformation = PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password,
            )
            EditTextView(
                text = passwords.newPassword,
                label = stringResource(R.string.new_password),
                onTextChange = { onEventSent(ProfileEvent.NewPasswordChanged(it)) },
                imeAction = ImeAction.Next,
                visualTransformation = PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password,
            )
            EditTextView(
                text = passwords.newPasswordAgain,
                label = stringResource(R.string.new_password_again),
                onTextChange = { onEventSent(ProfileEvent.NewPasswordAgainChanged(it)) },
                imeAction = ImeAction.Done,
                visualTransformation = PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password,
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
            ) {
                Button(
                    onClick = { onEventSent(ProfileEvent.ChangePasswordClicked) },
                ) {
                    Text(stringResource(R.string.save))
                }
            }
        }
    }
}