package com.dom.features.signup.data.mapper

import com.dom.features.signup.data.dto.SignUpDataDto
import com.dom.features.signup.domain.entity.SignUpData

fun SignUpData.toDto() = SignUpDataDto(login, password)

fun SignUpDataDto.toEntity() = SignUpData(login, passwordHash)