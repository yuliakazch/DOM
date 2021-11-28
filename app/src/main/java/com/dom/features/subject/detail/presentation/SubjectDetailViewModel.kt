package com.dom.features.subject.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dom.core.BaseViewModel
import com.dom.shared.subject.domain.usecase.DeleteSubjectUseCase
import com.dom.shared.subject.domain.usecase.GetSubjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getSubjectUseCase: GetSubjectUseCase,
    private val deleteSubjectUseCase: DeleteSubjectUseCase,
) : BaseViewModel<SubjectDetailEvent, SubjectDetailState, SubjectDetailEffect>() {

    private var subjectId: Int? = null

    override fun setInitialState(): SubjectDetailState =
        SubjectDetailState(data = null, loading = false)

    init {
        subjectId = savedStateHandle.get<Int>("subjectId")
        loadSubject()
    }

    private fun loadSubject() {
        subjectId?.let { id ->
            viewModelScope.launch {
                setState { copy(loading = true) }
                try {
                    val subject = getSubjectUseCase(id)
                    setState { copy(data = subject, loading = false) }
                } catch (e: Throwable) {
                    setState { copy(data = null, loading = false) }
                }
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
                subjectId?.let { id ->
                    setEffect { SubjectDetailEffect.Navigation.ToEditSubject(id) }
                }
            }
        }
    }

    private fun deleteSubject() {
        subjectId?.let { id ->
            viewModelScope.launch {
                setState { copy(loading = true) }
                try {
                    deleteSubjectUseCase(id)
                    setEffect { SubjectDetailEffect.Navigation.ToBack }
                } catch (e: Throwable) {
                    setState { copy(loading = false) }
                    setEffect { SubjectDetailEffect.Error() }
                }
            }
        }
    }
}