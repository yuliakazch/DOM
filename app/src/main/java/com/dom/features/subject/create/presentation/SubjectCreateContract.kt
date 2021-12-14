package com.dom.features.subject.create.presentation

import com.dom.core.ViewEvent
import com.dom.core.ViewSideEffect
import com.dom.core.ViewState

sealed class SubjectCreateEvent : ViewEvent {

    object BackClicked : SubjectCreateEvent()

    object CreateClicked : SubjectCreateEvent()

    data class NameChanged(val newValue: String) : SubjectCreateEvent()
    data class NoteChanged(val newValue: String) : SubjectCreateEvent()
    data class AmountChanged(val newValue: String) : SubjectCreateEvent()
    data class PriceChanged(val newValue: String) : SubjectCreateEvent()
    data class PrivateChanged(val newValue: Boolean) : SubjectCreateEvent()
}

data class SubjectCreateState(
    val data: SubjectCreateData,
    val loading: Boolean,
) : ViewState

sealed class SubjectCreateEffect : ViewSideEffect {

    data class Error(val message: String? = null) : SubjectCreateEffect()

    sealed class Navigation : SubjectCreateEffect() {

        object ToBack : Navigation()

        object ToHome : Navigation()
    }
}