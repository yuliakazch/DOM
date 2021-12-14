package com.dom.features.subject.create.presentation

import androidx.lifecycle.viewModelScope
import com.dom.core.BaseViewModel
import com.dom.shared.subject.domain.entity.Subject
import com.dom.shared.subject.domain.usecase.CreateSubjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectCreateViewModel @Inject constructor(
    private val createSubjectUseCase: CreateSubjectUseCase,
) : BaseViewModel<SubjectCreateEvent, SubjectCreateState, SubjectCreateEffect>() {

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
            name = name,
            note = note,
            price = price.toFloat(),
            amount = amount.toInt(),
            private = private,
        )
}