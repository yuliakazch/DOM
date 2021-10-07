package com.dom.features.signup.domain.usecases

import com.dom.features.signup.domain.entity.SignUpData
import com.dom.features.signup.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: SignUpRepository) {

    suspend operator fun invoke(data: SignUpData) {
        repository.signUp(data)
    }
}