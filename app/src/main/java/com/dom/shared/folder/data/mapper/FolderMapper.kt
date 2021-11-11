package com.dom.shared.folder.data.mapper

import com.dom.shared.folder.data.dto.FolderDto
import com.dom.shared.folder.domain.entity.Folder

fun Folder.toDto() = FolderDto(id, name)

fun FolderDto.toEntity() = Folder(id, name)

fun List<Folder>.toDtoList() = map { it.toDto() }

fun List<FolderDto>.toEntityList() = map { it.toEntity() }