package com.dom.features.subject.edit.presentation

import com.dom.core.ViewEvent
import com.dom.core.ViewSideEffect
import com.dom.core.ViewState

sealed class SubjectEditEvent : ViewEvent {

    object BackClicked : SubjectEditEvent()

    object SaveClicked : SubjectEditEvent()

    data class NameChanged(val newValue: String) : SubjectEditEvent()
    data class NoteChanged(val newValue: String) : SubjectEditEvent()
    data class AmountChanged(val newValue: String) : SubjectEditEvent()
    data class PriceChanged(val newValue: String) : SubjectEditEvent()
    data class PrivateChanged(val newValue: Boolean) : SubjectEditEvent()
}

data class SubjectEditState(
    val data: SubjectEditData?,
    val loading: Boolean,
) : ViewState

sealed class SubjectEditEffect : ViewSideEffect {

    data class Error(val message: String? = null) : SubjectEditEffect()

    sealed class Navigation : SubjectEditEffect() {

        object ToBack : Navigation()

        data class ToSubjectDetail(val id: Int) : Navigation()
    }
}