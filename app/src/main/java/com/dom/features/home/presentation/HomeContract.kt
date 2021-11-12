package com.dom.features.home.presentation

import com.dom.core.ViewEvent
import com.dom.core.ViewSideEffect
import com.dom.core.ViewState
import com.dom.shared.folder.domain.entity.Folder

sealed class HomeEvent : ViewEvent

data class HomeState(val data: List<Folder>?, val loading: Boolean) : ViewState

sealed class HomeEffect : ViewSideEffect {

	data class Error(val message: String? = null) : HomeEffect()

	sealed class Navigation : HomeEffect()
}