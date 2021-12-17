package com.dom.features.profile.presentation

import androidx.lifecycle.viewModelScope
import com.dom.core.BaseViewModel
import com.dom.shared.logout.domain.usecase.ClearTokenUseCase
import com.dom.shared.user.domain.usecase.DeleteUserUseCase
import com.dom.shared.user.domain.usecase.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val clearTokenUseCase: ClearTokenUseCase,
) : BaseViewModel<ProfileEvent, ProfileState, ProfileEffect>() {

    override fun setInitialState(): ProfileState =
        ProfileState(data = null, loading = false)

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                val profile = getProfileUseCase()
                setState { copy(data = profile, loading = false) }
            } catch (e: Throwable) {
                setState { copy(data = null, loading = false) }
            }
        }
    }

    override fun handleEvents(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.LogoutClicked -> {
                clearTokenUseCase()
                setEffect { ProfileEffect.Navigation.ToSignIn }
            }

            is ProfileEvent.DeleteClicked -> {
                deleteUser()
            }
        }
    }

    private fun deleteUser() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                deleteUserUseCase()
                clearTokenUseCase()
                setEffect { ProfileEffect.Navigation.ToSignIn }
            } catch (e: Throwable) {
                setState { copy(loading = false) }
                setEffect { ProfileEffect.Error() }
            }
        }
    }
}