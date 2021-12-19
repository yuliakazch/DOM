package com.dom.features.signup.presentation

import androidx.lifecycle.viewModelScope
import com.dom.core.BaseViewModel
import com.dom.shared.signup.domain.entity.SignUpData
import com.dom.shared.signup.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
	private val signUpUseCase: SignUpUseCase
) : BaseViewModel<SignUpEvent, SignUpState, SignUpEffect>() {

	override fun setInitialState(): SignUpState =
		SignUpState(content = SignUpContent())

	override fun handleEvents(event: SignUpEvent) {
		when (event) {
			is SignUpEvent.SignUpClicked        -> {
				signUp()
			}

			is SignUpEvent.AuthorizationClicked -> {
				setEffect { SignUpEffect.Navigation.ToAuthorization }
			}

			is SignUpEvent.LoginChanged         -> {
				setState {
					copy(content = content.copy(login = event.newValue))
				}
			}

			is SignUpEvent.PasswordChanged      -> {
				setState {
					copy(content = content.copy(password = event.newValue))
				}
			}

			is SignUpEvent.PasswordAgainChanged -> {
				setState {
					copy(content = content.copy(passwordAgain = event.newValue))
				}
			}
		}
	}

	private fun signUp() {
		viewModelScope.launch {
			setState { copy(loading = true) }
			try {
				val login = viewState.value.content.login
				val password = viewState.value.content.password
				val passwordAgain = viewState.value.content.passwordAgain
				if (password == passwordAgain) {
					signUpUseCase(SignUpData(login, password))
					setEffect { SignUpEffect.Navigation.ToAuthorization }
				} else {
					handleError()
				}
			} catch (e: Throwable) {
				handleError()
			}
		}
	}

	private fun handleError() {
		setState {
			copy(
				content = content.copy(password = "", passwordAgain = ""),
				loading = false
			)
		}
		setEffect { SignUpEffect.Error() }
	}
}