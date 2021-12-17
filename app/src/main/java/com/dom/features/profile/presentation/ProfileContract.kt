package com.dom.features.profile.presentation

import com.dom.core.ViewEvent
import com.dom.core.ViewSideEffect
import com.dom.core.ViewState
import com.dom.shared.user.domain.entity.Profile

sealed class ProfileEvent : ViewEvent {

    object LogoutClicked : ProfileEvent()

    object DeleteClicked : ProfileEvent()
}

data class ProfileState(val data: Profile?, val loading: Boolean) : ViewState

sealed class ProfileEffect : ViewSideEffect {

    data class Error(val message: String? = null) : ProfileEffect()

    sealed class Navigation : ProfileEffect() {

        object ToSignIn : Navigation()
    }
}