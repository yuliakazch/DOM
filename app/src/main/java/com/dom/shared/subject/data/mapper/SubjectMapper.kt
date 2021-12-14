package com.dom.shared.subject.data.mapper

import com.dom.shared.subject.data.dto.SubjectDto
import com.dom.shared.subject.domain.entity.Subject

fun Subject.toDto() = SubjectDto(id, name, note, price, amount, private)

fun SubjectDto.toEntity() = Subject(id, name, note, price, amount, private)

fun List<Subject>.toDtoList() = map { it.toDto() }

fun List<SubjectDto>.toEntityList() = map { it.toEntity() }