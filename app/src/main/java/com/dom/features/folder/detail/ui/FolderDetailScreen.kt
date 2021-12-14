package com.dom.features.folder.detail.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dom.R
import com.dom.components.error.ErrorView
import com.dom.components.progress.LoadingView
import com.dom.components.textfield.EditTextView
import com.dom.components.theme.VeryLightGray
import com.dom.components.topbar.TopBarFullView
import com.dom.core.LAUNCH_LISTEN_FOR_EFFECTS
import com.dom.features.folder.detail.presentation.FolderDetailEffect
import com.dom.features.folder.detail.presentation.FolderDetailEvent
import com.dom.features.folder.detail.presentation.FolderDetailState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun FolderDetailScreen(
    state: FolderDetailState,
    effectFlow: Flow<FolderDetailEffect>?,
    onEventSent: (event: FolderDetailEvent) -> Unit,
    onNavigationRequested: (navigationEffect: FolderDetailEffect.Navigation) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val textError = stringResource(R.string.error)

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is FolderDetailEffect.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = effect.message ?: textError,
                        duration = SnackbarDuration.Short
                    )
                }

                is FolderDetailEffect.Navigation ->
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

            else -> FolderDetailView(state, onEventSent)
        }
    }
}

@Composable
fun FolderDetailView(
    state: FolderDetailState,
    onEventSent: (event: FolderDetailEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarFullView(
                title = state.data?.name ?: stringResource(R.string.folder),
                oneIcon = Icons.Filled.Edit,
                twoIcon = Icons.Filled.Delete,
                onOneIconClicked = { onEventSent(FolderDetailEvent.ShowEditFolderDialogChanged(true)) },
                onTwoIconClicked = { onEventSent(FolderDetailEvent.DeleteFolderClicked) },
                onBackIconClicked = { onEventSent(FolderDetailEvent.BackClicked) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEventSent(FolderDetailEvent.CreateSubjectClicked) },
                backgroundColor = MaterialTheme.colors.primary,
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = null,
                )
            }
        },
    ) {
        Surface(
            color = VeryLightGray,
            modifier = Modifier.fillMaxSize()
        ) {
            if (state.editingFolder.showDialog) {
                EditFolderAlertDialog(state.editingFolder.nameFolder, onEventSent)
            }

            LazyColumn(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                state.data?.subjects?.forEach { subject ->
                    item {
                        SubjectItem(name = subject.name, image = null, onClick = { onEventSent(FolderDetailEvent.SubjectClicked(subject.id)) })
                    }
                }
            }
        }
    }
}

@Composable
fun EditFolderAlertDialog(
    nameFolder: String,
    onEventSent: (event: FolderDetailEvent) -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onEventSent(FolderDetailEvent.ShowEditFolderDialogChanged(false)) },
        text = {
            Column {
                Text(
                    text = stringResource(R.string.edit_name),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    textAlign = TextAlign.Center,
                )
                EditTextView(
                    text = nameFolder,
                    label = stringResource(R.string.name),
                    onTextChange = { onEventSent(FolderDetailEvent.NameFolderChanged(it)) },
                    imeAction = ImeAction.Done,
                )
            }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = { onEventSent(FolderDetailEvent.ShowEditFolderDialogChanged(false)) },
                    modifier = Modifier.padding(end = 64.dp),
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
                Button(
                    onClick = { onEventSent(FolderDetailEvent.UpdateFolderClicked) }
                ) {
                    Text(text = stringResource(R.string.save))
                }
            }
        },
    )
}