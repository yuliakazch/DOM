package com.dom.features.subject.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dom.core.BaseViewModel
import com.dom.shared.subject.domain.usecase.DeleteSubjectUseCase
import com.dom.shared.subject.domain.usecase.GetSubjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class SubjectDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getSubjectUseCase: GetSubjectUseCase,
    private val deleteSubjectUseCase: DeleteSubjectUseCase,
) : BaseViewModel<SubjectDetailEvent, SubjectDetailState, SubjectDetailEffect>() {

    private var folderId: Int = savedStateHandle.get<Int>("folderId") ?: throw NullPointerException("folderId is null")
    private var subjectId: Int = savedStateHandle.get<Int>("subjectId") ?: throw NullPointerException("subjectId is null")

    override fun setInitialState(): SubjectDetailState =
        SubjectDetailState(data = null, loading = false)

    init {
        loadSubject()
    }

    private fun loadSubject() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                val subject = getSubjectUseCase(subjectId)
                setState { copy(data = subject, loading = false) }
            } catch (e: Throwable) {
                setState { copy(data = null, loading = false) }
            }
        }
    }

    override fun handleEvents(event: SubjectDetailEvent) {
        when (event) {
            is SubjectDetailEvent.BackClicked -> {
                setEffect { SubjectDetailEffect.Navigation.ToBack }
            }

            is SubjectDetailEvent.DeleteSubjectClicked -> {
                deleteSubject()
            }

            is SubjectDetailEvent.UpdateSubjectClicked -> {
                setEffect { SubjectDetailEffect.Navigation.ToEditSubject(folderId, subjectId) }
            }
        }
    }

    private fun deleteSubject() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                deleteSubjectUseCase(subjectId)
                setEffect { SubjectDetailEffect.Navigation.ToHome }
            } catch (e: Throwable) {
                setState { copy(loading = false) }
                setEffect { SubjectDetailEffect.Error() }
            }
        }
    }
}