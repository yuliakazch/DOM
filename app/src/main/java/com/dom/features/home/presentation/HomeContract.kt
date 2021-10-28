package com.dom.features.home.presentation

import com.dom.shared.core.ViewEvent
import com.dom.shared.core.ViewSideEffect
import com.dom.shared.core.ViewState

sealed class HomeEvent : ViewEvent

data class HomeState(val loading: Boolean) : ViewState

sealed class HomeEffect : ViewSideEffect {

    data class Error(val message: String? = null) : HomeEffect()

    sealed class Navigation : HomeEffect()
}