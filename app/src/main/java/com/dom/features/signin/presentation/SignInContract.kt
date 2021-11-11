package com.dom.features.signin.presentation

import com.dom.core.ViewEvent
import com.dom.core.ViewSideEffect
import com.dom.core.ViewState
import com.dom.features.signin.domain.entity.Credentials

sealed class SignInEvent : ViewEvent {

	object SignInClicked : SignInEvent()

	object RegistrationClicked : SignInEvent()

	data class LoginChanged(val newValue: String) : SignInEvent()

	data class PasswordChanged(val newValue: String) : SignInEvent()
}

data class SignInState(val credentials: Credentials, val loading: Boolean = false) : ViewState

sealed class SignInEffect : ViewSideEffect {

	data class Error(val message: String? = null) : SignInEffect()

	sealed class Navigation : SignInEffect() {

		object ToHome : Navigation()

		object ToRegistration : Navigation()
	}
}