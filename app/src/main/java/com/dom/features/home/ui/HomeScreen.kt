package com.dom.features.home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.dom.components.topbar.TopBarView
import com.dom.core.LAUNCH_LISTEN_FOR_EFFECTS
import com.dom.features.home.presentation.CreationFolder
import com.dom.features.home.presentation.HomeEffect
import com.dom.features.home.presentation.HomeEvent
import com.dom.features.home.presentation.HomeState
import com.dom.shared.folder.domain.entity.Folder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
	state: HomeState,
	effectFlow: Flow<HomeEffect>?,
	onEventSent: (event: HomeEvent) -> Unit,
	onNavigationRequested: (navigationEffect: HomeEffect.Navigation) -> Unit
) {
	val scaffoldState: ScaffoldState = rememberScaffoldState()

	val textError = stringResource(R.string.error)

	LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
		effectFlow?.onEach { effect ->
			when (effect) {
				is HomeEffect.Error      -> {
					scaffoldState.snackbarHostState.showSnackbar(
						message = effect.message ?: textError,
						duration = SnackbarDuration.Short
					)
				}

				is HomeEffect.Navigation ->
					onNavigationRequested(effect)
			}
		}?.collect()
	}

	Scaffold(
		topBar = {
			TopBarView(
				title = stringResource(R.string.home_title),
			)
		},
	) {
		Surface(
			color = VeryLightGray,
			modifier = Modifier.fillMaxSize()
		) {
			when {
				state.loading      -> LoadingView()

				state.data == null -> ErrorView()

				else               -> HomeView(state.data, onEventSent, state.creationFolder)
			}
		}
	}
}

@ExperimentalFoundationApi
@Composable
fun HomeView(
	folders: List<Folder>,
	onEventSent: (event: HomeEvent) -> Unit,
	creationFolder: CreationFolder,
) {
	Scaffold(
		floatingActionButton = {
			FloatingActionButton(
				onClick = { onEventSent(HomeEvent.ShowNewFolderDialogChanged(true)) },
				modifier = Modifier.padding(bottom = 48.dp),
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
			if (creationFolder.showDialog) {
				CreationFolderAlertDialog(creationFolder.nameFolder, onEventSent)
			}
			LazyVerticalGrid(
				cells = GridCells.Fixed(2),
				modifier = Modifier.padding(top = 16.dp, bottom = 64.dp)
			) {
				folders.forEach { folder ->
					item {
						FolderItem(name = folder.name, image = null, onClick = { onEventSent(HomeEvent.FolderClicked(folder.id)) })
					}
				}
			}
		}
	}
}

@Composable
fun CreationFolderAlertDialog(
	nameFolder: String,
	onEventSent: (event: HomeEvent) -> Unit,
) {
	AlertDialog(
		onDismissRequest = { onEventSent(HomeEvent.ShowNewFolderDialogChanged(false)) },
		text = {
			Column {
				Text(
					text = stringResource(R.string.new_folder),
					style = MaterialTheme.typography.h6,
					modifier = Modifier
						.fillMaxWidth()
						.padding(bottom = 8.dp),
					textAlign = TextAlign.Center,
				)
				EditTextView(
					text = nameFolder,
					label = stringResource(R.string.name),
					onTextChange = { onEventSent(HomeEvent.NameNewFolderChanged(it)) },
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
					onClick = { onEventSent(HomeEvent.ShowNewFolderDialogChanged(false)) },
					modifier = Modifier.padding(end = 64.dp),
				) {
					Text(text = stringResource(R.string.cancel))
				}
				Button(
					onClick = { onEventSent(HomeEvent.CreateNewFolderClicked) }
				) {
					Text(text = stringResource(R.string.create))
				}
			}
		},
	)
}