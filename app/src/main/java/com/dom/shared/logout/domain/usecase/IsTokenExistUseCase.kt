package com.dom.shared.logout.domain.usecase

import com.dom.shared.logout.domain.repository.LogoutRepository
import javax.inject.Inject

class IsTokenExistUseCase @Inject constructor(private val repository: LogoutRepository) {

    operator fun invoke(): Boolean =
        repository.isTokenExist()
}