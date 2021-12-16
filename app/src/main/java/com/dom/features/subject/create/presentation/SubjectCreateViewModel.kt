package com.dom.features.subject.create.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dom.core.BaseViewModel
import com.dom.shared.subject.domain.entity.Subject
import com.dom.shared.subject.domain.usecase.CreateSubjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class SubjectCreateViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val createSubjectUseCase: CreateSubjectUseCase,
) : BaseViewModel<SubjectCreateEvent, SubjectCreateState, SubjectCreateEffect>() {

    private var folderId: Int = savedStateHandle.get<Int>("folderId") ?: throw NullPointerException("folderId is null")

    override fun setInitialState(): SubjectCreateState =
        SubjectCreateState(data = SubjectCreateData(), loading = false)

    override fun handleEvents(event: SubjectCreateEvent) {
        when (event) {
            is SubjectCreateEvent.BackClicked -> {
                setEffect { SubjectCreateEffect.Navigation.ToBack }
            }

            is SubjectCreateEvent.CreateClicked -> {
                createSubject()
            }

            is SubjectCreateEvent.NameChanged -> {
                setState { copy(data = data.copy(name = event.newValue)) }
            }

            is SubjectCreateEvent.NoteChanged -> {
                setState { copy(data = data.copy(note = event.newValue)) }
            }

            is SubjectCreateEvent.AmountChanged -> {
                setState { copy(data = data.copy(amount = event.newValue)) }
            }

            is SubjectCreateEvent.PriceChanged -> {
                setState { copy(data = data.copy(price = event.newValue)) }
            }

            is SubjectCreateEvent.PrivateChanged -> {
                setState { copy(data = data.copy(private = event.newValue)) }
            }
        }
    }

    private fun createSubject() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                createSubjectUseCase(viewState.value.data.convertToDomainData())
                setEffect { SubjectCreateEffect.Navigation.ToHome }
            } catch (e: Throwable) {
                setEffect { SubjectCreateEffect.Error() }
            }
        }
    }

    private fun SubjectCreateData.convertToDomainData(): Subject =
        Subject(
            folderId = folderId,
            name = name,
            note = note,
            price = if (price != "") price.toFloat() else 0f,
            amount = if (amount != "") amount.toInt() else 0,
            private = private,
        )
}