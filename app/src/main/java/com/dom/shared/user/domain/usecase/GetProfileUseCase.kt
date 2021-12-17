package com.dom.shared.user.domain.usecase

import com.dom.shared.user.domain.entity.Profile
import com.dom.shared.user.domain.repository.UserRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(): Profile =
        repository.getProfile()
}