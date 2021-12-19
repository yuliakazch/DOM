package com.dom.features.profile.presentation

import androidx.lifecycle.viewModelScope
import com.dom.core.BaseViewModel
import com.dom.shared.logout.domain.usecase.ClearTokenUseCase
import com.dom.shared.user.domain.entity.PasswordData
import com.dom.shared.user.domain.usecase.ChangePasswordUseCase
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
    private val changePasswordUseCase: ChangePasswordUseCase,
) : BaseViewModel<ProfileEvent, ProfileState, ProfileEffect>() {

    override fun setInitialState(): ProfileState =
        ProfileState(profile = null, passwords = PasswordContent(), loading = false)

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                val profile = getProfileUseCase()
                setState { copy(profile = profile, loading = false) }
            } catch (e: Throwable) {
                setState { copy(profile = null, loading = false) }
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

            is ProfileEvent.ChangePasswordClicked -> {
                changePassword()
            }

            is ProfileEvent.CurrentPasswordChanged -> {
                setState { copy(passwords = passwords.copy(currentPassword = event.newValue)) }
            }

            is ProfileEvent.NewPasswordChanged -> {
                setState { copy(passwords = passwords.copy(newPassword = event.newValue)) }
            }

            is ProfileEvent.NewPasswordAgainChanged -> {
                setState { copy(passwords = passwords.copy(newPasswordAgain = event.newValue)) }
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

    private fun changePassword() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                val currentPassword = viewState.value.passwords.currentPassword
                val newPassword = viewState.value.passwords.newPassword
                val newPasswordAgain = viewState.value.passwords.newPasswordAgain

                if (newPassword == newPasswordAgain) {
                    changePasswordUseCase(PasswordData(oldPassword = currentPassword, newPassword = newPassword))
                    setState { copy(passwords = PasswordContent(), loading = false) }
                } else {
                    setState { copy(passwords = passwords.copy(newPassword = "", newPasswordAgain = ""), loading = false) }
                    setEffect { ProfileEffect.Error() }
                }
            } catch (e: Throwable) {
                setState { copy(passwords = PasswordContent(), loading = false) }
                setEffect { ProfileEffect.Error() }
            }
        }
    }
}