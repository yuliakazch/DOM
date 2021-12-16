package com.dom.features.subject.edit.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dom.core.BaseViewModel
import com.dom.shared.subject.domain.entity.Subject
import com.dom.shared.subject.domain.usecase.GetSubjectUseCase
import com.dom.shared.subject.domain.usecase.UpdateSubjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class SubjectEditViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getSubjectUseCase: GetSubjectUseCase,
    private val updateSubjectUseCase: UpdateSubjectUseCase,
) : BaseViewModel<SubjectEditEvent, SubjectEditState, SubjectEditEffect>() {

    private var folderId: Int = savedStateHandle.get<Int>("folderId") ?: throw NullPointerException("folderId is null")
    private var subjectId: Int = savedStateHandle.get<Int>("subjectId") ?: throw NullPointerException("subjectId is null")

    override fun setInitialState(): SubjectEditState =
        SubjectEditState(data = null, loading = false)

    init {
        loadSubject()
    }

    private fun loadSubject() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                val subject = getSubjectUseCase(subjectId)
                setState { copy(data = subject.convertToPresentationData(), loading = false) }
            } catch (e: Throwable) {
                setState { copy(data = null, loading = false) }
            }
        }
    }

    private fun Subject.convertToPresentationData(): SubjectEditData =
        SubjectEditData(
            id = id,
            name = name,
            note = note,
            price = price.toString(),
            amount = amount.toString(),
            private = private,
        )

    override fun handleEvents(event: SubjectEditEvent) {
        when (event) {
            is SubjectEditEvent.BackClicked -> {
                setEffect { SubjectEditEffect.Navigation.ToBack }
            }

            is SubjectEditEvent.SaveClicked -> {
                updateSubject()
            }

            is SubjectEditEvent.NameChanged -> {
                setState { copy(data = data?.copy(name = event.newValue)) }
            }

            is SubjectEditEvent.NoteChanged -> {
                setState { copy(data = data?.copy(note = event.newValue)) }
            }

            is SubjectEditEvent.AmountChanged -> {
                setState { copy(data = data?.copy(amount = event.newValue)) }
            }

            is SubjectEditEvent.PriceChanged -> {
                setState { copy(data = data?.copy(price = event.newValue)) }
            }

            is SubjectEditEvent.PrivateChanged -> {
                setState { copy(data = data?.copy(private = event.newValue)) }
            }
        }
    }

    private fun updateSubject() {
        viewState.value.data?.let { data ->
            viewModelScope.launch {
                setState { copy(loading = true) }
                try {
                    updateSubjectUseCase(data.convertToDomainData())
                    setEffect { SubjectEditEffect.Navigation.ToHome }
                } catch (e: Throwable) {
                    setEffect { SubjectEditEffect.Error() }
                }
            }
        }
    }

    private fun SubjectEditData.convertToDomainData(): Subject =
        Subject(
            id = id,
            folderId = folderId,
            name = name,
            note = note,
            price = price.toFloat(),
            amount = amount.toInt(),
            private = private,
        )
}