package com.dom.shared.user.domain.usecase

import com.dom.shared.user.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke() {
        repository.delete()
    }
}