package com.dom.features.signin.presentation

import com.dom.features.signin.domain.entity.Credentials
import com.dom.shared.core.ViewEvent
import com.dom.shared.core.ViewSideEffect
import com.dom.shared.core.ViewState

sealed class SignInEvent : ViewEvent {

    object SignInClicked : SignInEvent()

    object RegistrationClicked : SignInEvent()

    data class LoginChanged(val newValue: String) : SignInEvent()

    data class PasswordChanged(val newValue: String) : SignInEvent()
}

data class SignInState(val credentials: Credentials, val isLoading: Boolean = false) : ViewState

sealed class SignInEffect : ViewSideEffect {

    data class Error(val message: String? = null) : SignInEffect()

    sealed class Navigation : SignInEffect() {

        object ToHome : Navigation()

        object ToRegistration : Navigation()
    }
}