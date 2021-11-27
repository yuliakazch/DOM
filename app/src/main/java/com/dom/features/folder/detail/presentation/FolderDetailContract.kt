package com.dom.features.folder.detail.presentation

import com.dom.core.ViewEvent
import com.dom.core.ViewSideEffect
import com.dom.core.ViewState
import com.dom.shared.folder.domain.entity.FolderInfo

sealed class FolderDetailEvent : ViewEvent {

	object UpdateFolderClicked : FolderDetailEvent()

	object DeleteFolderClicked : FolderDetailEvent()

	object BackClicked : FolderDetailEvent()

	data class ShowEditFolderDialogChanged(val newValue: Boolean) : FolderDetailEvent()

	data class NameFolderChanged(val newValue: String) : FolderDetailEvent()
}

data class FolderDetailState(
	val data: FolderInfo?,
	val loading: Boolean,
	val editingFolder: EditingFolder,
) : ViewState

sealed class FolderDetailEffect : ViewSideEffect {

	data class Error(val message: String? = null) : FolderDetailEffect()

	sealed class Navigation : FolderDetailEffect() {

		object ToBack : Navigation()
	}
}