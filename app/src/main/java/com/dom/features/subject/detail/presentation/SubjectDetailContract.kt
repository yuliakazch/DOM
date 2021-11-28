package com.dom.features.subject.detail.presentation

import com.dom.core.ViewEvent
import com.dom.core.ViewSideEffect
import com.dom.core.ViewState
import com.dom.shared.subject.domain.entity.Subject

sealed class SubjectDetailEvent : ViewEvent {

    object UpdateSubjectClicked : SubjectDetailEvent()

    object DeleteSubjectClicked : SubjectDetailEvent()

    object BackClicked : SubjectDetailEvent()
}

data class SubjectDetailState(
    val data: Subject?,
    val loading: Boolean,
) : ViewState

sealed class SubjectDetailEffect : ViewSideEffect {

    data class Error(val message: String? = null) : SubjectDetailEffect()

    sealed class Navigation : SubjectDetailEffect() {

        object ToBack : Navigation()

        data class ToEditSubject(val subjectId: Int) : Navigation()
    }
}