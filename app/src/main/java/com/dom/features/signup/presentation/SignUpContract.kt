package com.dom.features.signup.presentation

import com.dom.shared.core.ViewEvent
import com.dom.shared.core.ViewSideEffect
import com.dom.shared.core.ViewState

sealed class SignUpEvent : ViewEvent {

    object SignUpClicked : SignUpEvent()

    object AuthorizationClicked : SignUpEvent()

    data class LoginChanged(val newValue: String) : SignUpEvent()

    data class PasswordChanged(val newValue: String) : SignUpEvent()

    data class PasswordAgainChanged(val newValue: String) : SignUpEvent()
}

data class SignUpState(val content: SignUpContent, val isLoading: Boolean = false) : ViewState

sealed class SignUpEffect : ViewSideEffect {

    data class Error(val message: String? = null) : SignUpEffect()

    sealed class Navigation : SignUpEffect() {

        object ToAuthorization : Navigation()
    }
}