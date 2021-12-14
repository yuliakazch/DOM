package com.dom.features.subject.edit.ui

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
import com.dom.components.error.ErrorView
import com.dom.components.progress.LoadingView
import com.dom.components.textfield.EditTextView
import com.dom.components.theme.VeryLightGray
import com.dom.components.topbar.TopBarBackView
import com.dom.core.LAUNCH_LISTEN_FOR_EFFECTS
import com.dom.features.subject.edit.presentation.SubjectEditData
import com.dom.features.subject.edit.presentation.SubjectEditEffect
import com.dom.features.subject.edit.presentation.SubjectEditEvent
import com.dom.features.subject.edit.presentation.SubjectEditState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SubjectEditScreen(
    state: SubjectEditState,
    effectFlow: Flow<SubjectEditEffect>?,
    onEventSent: (event: SubjectEditEvent) -> Unit,
    onNavigationRequested: (navigationEffect: SubjectEditEffect.Navigation) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val textError = stringResource(R.string.error)

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is SubjectEditEffect.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = effect.message ?: textError,
                        duration = SnackbarDuration.Short
                    )
                }

                is SubjectEditEffect.Navigation ->
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

            else -> SubjectEditView(state.data, onEventSent)
        }
    }
}

@Composable
fun SubjectEditView(
    subject: SubjectEditData,
    onEventSent: (event: SubjectEditEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarBackView(
                title = stringResource(R.string.subject_edit),
                onIconClicked = { onEventSent(SubjectEditEvent.BackClicked) },
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
                        onTextChange = { onEventSent(SubjectEditEvent.NameChanged(it)) },
                        imeAction = ImeAction.Next,
                        singleLine = false,
                    )
                }
                item {
                    EditTextView(
                        text = subject.note,
                        label = stringResource(R.string.note),
                        onTextChange = { onEventSent(SubjectEditEvent.NoteChanged(it)) },
                        imeAction = ImeAction.Next,
                        singleLine = false,
                    )
                }
                item {
                    EditTextView(
                        text = subject.amount,
                        label = stringResource(R.string.amount),
                        onTextChange = { onEventSent(SubjectEditEvent.AmountChanged(it)) },
                        imeAction = ImeAction.Next,
                        singleLine = false,
                    )
                }
                item {
                    EditTextView(
                        text = subject.price,
                        label = stringResource(R.string.price),
                        onTextChange = { onEventSent(SubjectEditEvent.PriceChanged(it)) },
                        imeAction = ImeAction.Next,
                        singleLine = false,
                    )
                }
                item {
                    TextWithCheckboxView(
                        text = stringResource(R.string.private_subject),
                        checked = subject.private,
                        onCheckedChange = { onEventSent(SubjectEditEvent.PrivateChanged(it)) }
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
                            onClick = { onEventSent(SubjectEditEvent.SaveClicked) },
                        ) {
                            Text(stringResource(R.string.save))
                        }
                    }
                }
            }
        }
    }
}