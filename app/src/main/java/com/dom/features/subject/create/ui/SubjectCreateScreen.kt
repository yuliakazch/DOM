package com.dom.features.subject.create.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.dom.R
import com.dom.components.checkbox.TextWithCheckboxView
import com.dom.components.progress.LoadingView
import com.dom.components.textfield.EditTextView
import com.dom.components.theme.VeryLightGray
import com.dom.components.topbar.TopBarBackView
import com.dom.core.LAUNCH_LISTEN_FOR_EFFECTS
import com.dom.features.subject.create.presentation.SubjectCreateData
import com.dom.features.subject.create.presentation.SubjectCreateEffect
import com.dom.features.subject.create.presentation.SubjectCreateEvent
import com.dom.features.subject.create.presentation.SubjectCreateState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SubjectCreateScreen(
    state: SubjectCreateState,
    effectFlow: Flow<SubjectCreateEffect>?,
    onEventSent: (event: SubjectCreateEvent) -> Unit,
    onNavigationRequested: (navigationEffect: SubjectCreateEffect.Navigation) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val textError = stringResource(R.string.error)

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is SubjectCreateEffect.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = effect.message ?: textError,
                        duration = SnackbarDuration.Short
                    )
                }

                is SubjectCreateEffect.Navigation ->
                    onNavigationRequested(effect)
            }
        }?.collect()
    }

    Surface(
        color = VeryLightGray,
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.loading) {
            LoadingView()
        } else {
            SubjectCreateView(state.data, onEventSent)
        }
    }
}

@Composable
fun SubjectCreateView(
    subject: SubjectCreateData,
    onEventSent: (event: SubjectCreateEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarBackView(
                title = stringResource(R.string.subject_create),
                onIconClicked = { onEventSent(SubjectCreateEvent.BackClicked) },
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
                    EditTextView(
                        text = subject.name,
                        label = stringResource(R.string.name),
                        onTextChange = { onEventSent(SubjectCreateEvent.NameChanged(it)) },
                        imeAction = ImeAction.Next,
                        singleLine = false,
                    )
                }
                item {
                    EditTextView(
                        text = subject.note,
                        label = stringResource(R.string.note),
                        onTextChange = { onEventSent(SubjectCreateEvent.NoteChanged(it)) },
                        imeAction = ImeAction.Next,
                        singleLine = false,
                    )
                }
                item {
                    EditTextView(
                        text = subject.amount,
                        label = stringResource(R.string.amount),
                        onTextChange = { onEventSent(SubjectCreateEvent.AmountChanged(it)) },
                        imeAction = ImeAction.Next,
                        singleLine = false,
                    )
                }
                item {
                    EditTextView(
                        text = subject.price,
                        label = stringResource(R.string.price),
                        onTextChange = { onEventSent(SubjectCreateEvent.PriceChanged(it)) },
                        imeAction = ImeAction.Next,
                        singleLine = false,
                    )
                }
                item {
                    TextWithCheckboxView(
                        text = stringResource(R.string.private_subject),
                        checked = subject.private,
                        onCheckedChange = { onEventSent(SubjectCreateEvent.PrivateChanged(it)) }
                    )
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Button(
                            onClick = { onEventSent(SubjectCreateEvent.CreateClicked) },
                        ) {
                            Text(stringResource(R.string.create))
                        }
                    }
                }
            }
        }
    }
}