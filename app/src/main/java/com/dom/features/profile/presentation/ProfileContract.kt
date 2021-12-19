package com.dom.features.profile.presentation

import com.dom.core.ViewEvent
import com.dom.core.ViewSideEffect
import com.dom.core.ViewState
import com.dom.shared.user.domain.entity.Profile

sealed class ProfileEvent : ViewEvent {

    object LogoutClicked : ProfileEvent()

    object DeleteClicked : ProfileEvent()

    object ChangePasswordClicked : ProfileEvent()

    data class CurrentPasswordChanged(val newValue: String) : ProfileEvent()

    data class NewPasswordChanged(val newValue: String) : ProfileEvent()

    data class NewPasswordAgainChanged(val newValue: String) : ProfileEvent()
}

data class ProfileState(
    val profile: Profile?,
    val passwords: PasswordContent,
    val loading: Boolean,
) : ViewState

sealed class ProfileEffect : ViewSideEffect {

    data class Error(val message: String? = null) : ProfileEffect()

    sealed class Navigation : ProfileEffect() {

        object ToSignIn : Navigation()
    }
}