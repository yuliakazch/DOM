package com.dom.features.subject.detail.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dom.R
import com.dom.components.error.ErrorView
import com.dom.components.progress.LoadingView
import com.dom.components.text.TwoTextView
import com.dom.components.theme.VeryLightGray
import com.dom.components.topbar.TopBarFullView
import com.dom.core.LAUNCH_LISTEN_FOR_EFFECTS
import com.dom.features.subject.detail.presentation.SubjectDetailEffect
import com.dom.features.subject.detail.presentation.SubjectDetailEvent
import com.dom.features.subject.detail.presentation.SubjectDetailState
import com.dom.shared.subject.domain.entity.Subject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SubjectDetailScreen(
    state: SubjectDetailState,
    effectFlow: Flow<SubjectDetailEffect>?,
    onEventSent: (event: SubjectDetailEvent) -> Unit,
    onNavigationRequested: (navigationEffect: SubjectDetailEffect.Navigation) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val textError = stringResource(R.string.error)

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is SubjectDetailEffect.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = effect.message ?: textError,
                        duration = SnackbarDuration.Short
                    )
                }

                is SubjectDetailEffect.Navigation ->
                    onNavigationRequested(effect)
            }
        }?.collect()
    }

    Surface(
        color = VeryLightGray,
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.loading -> LoadingView()

            state.data == null -> ErrorView()

            else -> SubjectDetailView(state.data, onEventSent)
        }
    }
}

@Composable
fun SubjectDetailView(
    subject: Subject,
    onEventSent: (event: SubjectDetailEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarFullView(
                title = subject.name ?: stringResource(R.string.subject),
                oneIcon = Icons.Filled.Edit,
                twoIcon = Icons.Filled.Delete,
                onOneIconClicked = { onEventSent(SubjectDetailEvent.UpdateSubjectClicked) },
                onTwoIconClicked = { onEventSent(SubjectDetailEvent.DeleteSubjectClicked) },
                onBackIconClicked = { onEventSent(SubjectDetailEvent.BackClicked) },
            )
        },
    ) {
        Surface(
            color = VeryLightGray,
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                item {
                    TwoTextView(
                        textOne = stringResource(R.string.name),
                        textTwo = subject.name,
                    )
                }
                item {
                    TwoTextView(
                        textOne = stringResource(R.string.note),
                        textTwo = subject.note,
                    )
                }
                item {
                    TwoTextView(
                        textOne = stringResource(R.string.amount),
                        textTwo = subject.amount.toString(),
                    )
                }
                item {
                    TwoTextView(
                        textOne = stringResource(R.string.price),
                        textTwo = subject.price.toString(),
                    )
                }
                item {
                    TwoTextView(
                        textOne = stringResource(R.string.private_subject),
                        textTwo = if (subject.private) stringResource(R.string.yes) else stringResource(R.string.no),
                    )
                }
            }
        }
    }
}