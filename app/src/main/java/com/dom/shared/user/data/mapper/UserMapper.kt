package com.dom.shared.user.data.mapper

import com.dom.shared.user.data.dto.PasswordDataDto
import com.dom.shared.user.data.dto.ProfileDto
import com.dom.shared.user.domain.entity.PasswordData
import com.dom.shared.user.domain.entity.Profile
import org.apache.commons.codec.digest.DigestUtils

fun Profile.toDto() = ProfileDto(username)

fun ProfileDto.toEntity() = Profile(username)

fun PasswordData.toDto() = PasswordDataDto(
    oldPasswordHash = DigestUtils.md5Hex(oldPassword),
    newPasswordHash = DigestUtils.md5Hex(newPassword),
)