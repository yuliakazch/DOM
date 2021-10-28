package com.dom.features.profile.presentation

import com.dom.shared.base.ViewEvent
import com.dom.shared.base.ViewSideEffect
import com.dom.shared.base.ViewState

sealed class ProfileEvent : ViewEvent {

    object LogoutClicked : ProfileEvent()
}

data class ProfileState(val loading: Boolean) : ViewState

sealed class ProfileEffect : ViewSideEffect {

    data class Error(val message: String? = null) : ProfileEffect()

    sealed class Navigation : ProfileEffect() {

        object ToSignIn : Navigation()
    }
}