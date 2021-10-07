package com.dom.features.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dom.features.signup.domain.entity.SignUpData
import com.dom.features.signup.domain.usecases.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    fun signUp(login: String, password: String) {
        viewModelScope.launch {
            try {
                signUpUseCase(SignUpData(login, password))
                // TODO: go to signIn
            } catch (e: Throwable) {

            }
        }
    }
}