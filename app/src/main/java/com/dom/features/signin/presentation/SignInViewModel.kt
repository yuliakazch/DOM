package com.dom.features.signin.presentation

import androidx.lifecycle.viewModelScope
import com.dom.R
import com.dom.features.signin.domain.entity.Credentials
import com.dom.features.signin.domain.usecases.SignInUseCase
import com.dom.shared.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel<SignInEvent, SignInState, SignInEffect>() {

    override fun setInitialState(): SignInState =
        SignInState(credentials = Credentials(login = "", password = ""), isLoading = false)

    override fun handleEvents(event: SignInEvent) {
        when (event) {
            is SignInEvent.SignInClicked -> {
                login()
            }

            is SignInEvent.RegistrationClicked -> {
                setEffect { SignInEffect.Navigation.ToRegistration }
            }

            is SignInEvent.LoginChanged -> {
                setState {
                    copy(credentials = credentials.copy(login = event.newValue))
                }
            }

            is SignInEvent.PasswordChanged -> {
                setState {
                    copy(credentials = credentials.copy(password = event.newValue))
                }
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            try {
                signInUseCase(viewState.value.credentials)
                setEffect { SignInEffect.Navigation.ToHome }
            } catch (e: Throwable) {
                setState { copy(credentials = credentials.copy(password = ""), isLoading = false) }
                setEffect { SignInEffect.Error() }
            }
        }
    }
}