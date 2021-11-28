package com.dom.features.folder.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dom.core.BaseViewModel
import com.dom.shared.folder.domain.entity.Folder
import com.dom.shared.folder.domain.usecase.DeleteFolderUseCase
import com.dom.shared.folder.domain.usecase.GetFolderInfoUseCase
import com.dom.shared.folder.domain.usecase.UpdateFolderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getFolderInfoUseCase: GetFolderInfoUseCase,
    private val updateFolderUseCase: UpdateFolderUseCase,
    private val deleteFolderUseCase: DeleteFolderUseCase,
) : BaseViewModel<FolderDetailEvent, FolderDetailState, FolderDetailEffect>() {

    private var folderId: Int? = null

    override fun setInitialState(): FolderDetailState =
        FolderDetailState(data = null, loading = false, editingFolder = EditingFolder())

    init {
        folderId = savedStateHandle.get<Int>("folderId")
        loadFolderInfo()
    }

    private fun loadFolderInfo() {
        folderId?.let { id ->
            viewModelScope.launch {
                setState { copy(loading = true) }
                try {
                    val info = getFolderInfoUseCase(id)
                    setState { copy(data = info, loading = false, editingFolder = editingFolder.copy(nameFolder = info.name ?: "")) }
                } catch (e: Throwable) {
                    setState { copy(data = null, loading = false) }
                }
            }
        }
    }

    override fun handleEvents(event: FolderDetailEvent) {
        when (event) {
            is FolderDetailEvent.UpdateFolderClicked -> {
                updateFolder()
            }

            is FolderDetailEvent.DeleteFolderClicked -> {
                deleteFolder()
            }

            is FolderDetailEvent.BackClicked -> {
                setEffect { FolderDetailEffect.Navigation.ToBack }
            }

            is FolderDetailEvent.SubjectClicked -> {
                setEffect { FolderDetailEffect.Navigation.ToSubjectDetail(event.subjectId) }
            }

            is FolderDetailEvent.ShowEditFolderDialogChanged -> {
                setState {
                    copy(editingFolder = editingFolder.copy(showDialog = event.newValue))
                }
            }

            is FolderDetailEvent.NameFolderChanged -> {
                setState {
                    copy(editingFolder = editingFolder.copy(nameFolder = event.newValue))
                }
            }
        }
    }

    private fun updateFolder() {
        folderId?.let { id ->
            viewModelScope.launch {
                setState { copy(loading = true) }
                try {
                    val name = viewState.value.editingFolder.nameFolder
                    updateFolderUseCase(Folder(id, name))
                    setState { copy(editingFolder = EditingFolder(nameFolder = name)) }
                    loadFolderInfo()
                } catch (e: Throwable) {
                    setState { copy(loading = false, editingFolder = EditingFolder()) }
                    setEffect { FolderDetailEffect.Error() }
                }
            }
        }
    }

    private fun deleteFolder() {
        folderId?.let { id ->
            viewModelScope.launch {
                setState { copy(loading = true) }
                try {
                    deleteFolderUseCase(id)
                    setEffect { FolderDetailEffect.Navigation.ToBack }
                } catch (e: Throwable) {
                    setState { copy(loading = false) }
                    setEffect { FolderDetailEffect.Error() }
                }
            }
        }
    }
}