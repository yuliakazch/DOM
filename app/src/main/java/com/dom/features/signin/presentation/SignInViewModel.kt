package com.dom.features.signin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dom.features.signin.domain.entity.Credentials
import com.dom.features.signin.domain.usecases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    fun login(login: String, password: String) {
        viewModelScope.launch {
            try {
                signInUseCase(Credentials(login, password))
                // TODO: go to home
            } catch (e: Throwable) {

            }
        }
    }
}