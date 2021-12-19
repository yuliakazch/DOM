package com.dom.shared.signup.domain.usecase

import com.dom.shared.signup.domain.entity.SignUpData
import com.dom.shared.signup.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: SignUpRepository) {

    suspend operator fun invoke(data: SignUpData) {
        repository.signUp(data)
    }
}