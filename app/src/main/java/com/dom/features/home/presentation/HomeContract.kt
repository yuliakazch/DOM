package com.dom.features.home.presentation

import com.dom.core.ViewEvent
import com.dom.core.ViewSideEffect
import com.dom.core.ViewState
import com.dom.shared.folder.domain.entity.Folder

sealed class HomeEvent : ViewEvent {

	object CreateNewFolderClicked : HomeEvent()

	data class FolderClicked(val folderId: Int) : HomeEvent()

	data class ShowNewFolderDialogChanged(val newValue: Boolean) : HomeEvent()

	data class NameNewFolderChanged(val newValue: String) : HomeEvent()
}

data class HomeState(
	val data: List<Folder>?,
	val loading: Boolean,
	val creationFolder: CreationFolder
) : ViewState

sealed class HomeEffect : ViewSideEffect {

	data class Error(val message: String? = null) : HomeEffect()

	sealed class Navigation : HomeEffect() {

		data class ToFolderDetail(val folderId: Int) : Navigation()
	}
}