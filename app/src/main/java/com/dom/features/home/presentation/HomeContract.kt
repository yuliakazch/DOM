package com.dom.features.home.presentation

import com.dom.shared.base.ViewEvent
import com.dom.shared.base.ViewSideEffect
import com.dom.shared.base.ViewState

sealed class HomeEvent : ViewEvent

data class HomeState(val loading: Boolean) : ViewState

sealed class HomeEffect : ViewSideEffect {

    data class Error(val message: String? = null) : HomeEffect()

    sealed class Navigation : HomeEffect()
}