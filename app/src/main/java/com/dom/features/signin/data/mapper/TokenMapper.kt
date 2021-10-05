package com.dom.features.signin.data.mapper

import com.dom.features.signin.data.dto.TokenDto
import com.dom.features.signin.domain.entity.Token

fun Token.toDto() = TokenDto(value)

fun TokenDto.toEntity() = Token(value)