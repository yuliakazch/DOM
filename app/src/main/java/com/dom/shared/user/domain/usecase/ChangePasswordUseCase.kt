package com.dom.shared.user.domain.usecase

import com.dom.shared.user.domain.entity.PasswordData
import com.dom.shared.user.domain.repository.UserRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(data: PasswordData) {
        repository.changePassword(data)
    }
}