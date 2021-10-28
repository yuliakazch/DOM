package com.dom.shared.logout.domain.usecase

import com.dom.shared.logout.domain.repository.LogoutRepository
import javax.inject.Inject

class ClearTokenUseCase @Inject constructor(private val repository: LogoutRepository) {

    operator fun invoke() {
        repository.clearToken()
    }
}