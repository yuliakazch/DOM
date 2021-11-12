package com.dom.features.home.presentation

import androidx.lifecycle.viewModelScope
import com.dom.core.BaseViewModel
import com.dom.shared.folder.domain.usecase.GetFoldersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getFoldersUseCase: GetFoldersUseCase,
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

	override fun setInitialState(): HomeState =
		HomeState(data = null, loading = true)

	init {
		loadFolders()
	}

	private fun loadFolders() {
		viewModelScope.launch {
			try {
				val folders = getFoldersUseCase()
				setState { copy(data = folders, loading = false) }
			} catch (e: Throwable) {
				setState { copy(data = null, loading = false) }
			}
		}
	}

	override fun handleEvents(event: HomeEvent) {

	}
}