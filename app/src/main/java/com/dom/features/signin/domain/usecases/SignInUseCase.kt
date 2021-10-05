package com.dom.features.signin.domain.usecases

import com.dom.features.signin.domain.entity.Credentials
import com.dom.features.signin.domain.repository.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: SignInRepository) {

    suspend operator fun invoke(credentials: Credentials) {
        repository.signIn(credentials)
    }
}