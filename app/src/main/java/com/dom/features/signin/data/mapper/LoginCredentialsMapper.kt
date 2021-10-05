package com.dom.features.signin.data.mapper

import com.dom.features.signin.data.dto.CredentialsDto
import com.dom.features.signin.domain.entity.Credentials

fun Credentials.toDto() = CredentialsDto(login, password)

fun CredentialsDto.toEntity() = Credentials(login, password)