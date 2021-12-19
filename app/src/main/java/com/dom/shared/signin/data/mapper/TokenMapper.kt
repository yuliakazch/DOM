package com.dom.shared.signin.data.mapper

import com.dom.shared.signin.data.dto.TokenDto
import com.dom.shared.signin.domain.entity.Token

fun Token.toDto() = TokenDto(value)

fun TokenDto.toEntity() = Token(value)