package com.dom.shared.folder.data.mapper

import com.dom.shared.folder.data.dto.SubjectDto
import com.dom.shared.folder.domain.entity.Subject

fun Subject.toDto() = SubjectDto(id, name)

fun SubjectDto.toEntity() = Subject(id, name)

fun List<Subject>.toDtoList() = map { it.toDto() }

fun List<SubjectDto>.toEntityList() = map { it.toEntity() }