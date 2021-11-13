package com.dom.features.home.presentation

import androidx.lifecycle.viewModelScope
import com.dom.core.BaseViewModel
import com.dom.shared.folder.domain.entity.Folder
import com.dom.shared.folder.domain.usecase.CreateFolderUseCase
import com.dom.shared.folder.domain.usecase.GetFoldersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getFoldersUseCase: GetFoldersUseCase,
	private val createFolderUseCase: CreateFolderUseCase,
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

	override fun setInitialState(): HomeState =
		HomeState(data = null, loading = false, creationFolder = CreationFolder())

	init {
		loadFolders()
	}

	private fun loadFolders() {
		viewModelScope.launch {
			setState { copy(loading = true) }
			try {
				val folders = getFoldersUseCase()
				setState { copy(data = folders, loading = false) }
			} catch (e: Throwable) {
				setState { copy(data = null, loading = false) }
			}
		}
	}

	override fun handleEvents(event: HomeEvent) {
		when (event) {
			is HomeEvent.CreateNewFolderClicked     -> {
				if (isAvailableLengthNameFolder()) {
					setState {
						copy(creationFolder = creationFolder.copy(showDialog = false))
					}
					createFolder()
				}
			}

			is HomeEvent.ShowNewFolderDialogChanged -> {
				setState {
					copy(creationFolder = creationFolder.copy(nameFolder = "", showDialog = event.newValue))
				}
			}

			is HomeEvent.NameNewFolderChanged       -> {
				setState {
					copy(creationFolder = creationFolder.copy(nameFolder = event.newValue))
				}
			}
		}
	}

	private fun createFolder() {
		viewModelScope.launch {
			setState { copy(loading = true) }
			try {
				val folder = Folder(name = viewState.value.creationFolder.nameFolder)
				createFolderUseCase(folder)
				setState { copy(creationFolder = CreationFolder()) }
				loadFolders()
			} catch (e: Throwable) {
				setState { copy(loading = false, creationFolder = CreationFolder()) }
				setEffect { HomeEffect.Error() }
			}
		}
	}

	private fun isAvailableLengthNameFolder(): Boolean =
		viewState.value.creationFolder.nameFolder.length < 20
}