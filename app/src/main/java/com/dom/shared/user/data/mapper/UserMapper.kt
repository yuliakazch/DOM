package com.dom.shared.user.data.mapper

import com.dom.shared.user.data.dto.ProfileDto
import com.dom.shared.user.domain.entity.Profile

fun Profile.toDto() = ProfileDto(username)

fun ProfileDto.toEntity() = Profile(username)