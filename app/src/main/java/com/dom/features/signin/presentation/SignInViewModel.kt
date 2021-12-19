package com.dom.features.signin.presentation

import androidx.lifecycle.viewModelScope
import com.dom.core.BaseViewModel
import com.dom.shared.signin.domain.entity.Credentials
import com.dom.shared.signin.domain.usecases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
	private val signInUseCase: SignInUseCase
) : BaseViewModel<SignInEvent, SignInState, SignInEffect>() {

	override fun setInitialState(): SignInState =
		SignInState(credentials = Credentials(login = "", password = ""), loading = false)

	override fun handleEvents(event: SignInEvent) {
		when (event) {
			is SignInEvent.SignInClicked       -> {
				signIn()
			}

			is SignInEvent.RegistrationClicked -> {
				setEffect { SignInEffect.Navigation.ToRegistration }
			}

			is SignInEvent.LoginChanged        -> {
				setState {
					copy(credentials = credentials.copy(login = event.newValue))
				}
			}

			is SignInEvent.PasswordChanged     -> {
				setState {
					copy(credentials = credentials.copy(password = event.newValue))
				}
			}
		}
	}

	private fun signIn() {
		viewModelScope.launch {
			setState { copy(loading = true) }
			try {
				signInUseCase(viewState.value.credentials)
				setEffect { SignInEffect.Navigation.ToHome }
			} catch (e: Throwable) {
				setState { copy(credentials = credentials.copy(password = ""), loading = false) }
				setEffect { SignInEffect.Error() }
			}
		}
	}
}