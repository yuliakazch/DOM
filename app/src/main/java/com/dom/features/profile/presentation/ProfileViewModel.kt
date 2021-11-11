package com.dom.features.profile.presentation

import com.dom.core.BaseViewModel
import com.dom.shared.logout.domain.usecase.ClearTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val clearTokenUseCase: ClearTokenUseCase,
) : BaseViewModel<ProfileEvent, ProfileState, ProfileEffect>() {

    override fun setInitialState(): ProfileState =
        ProfileState(loading = false)

    override fun handleEvents(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.LogoutClicked -> {
                clearTokenUseCase()
                setEffect { ProfileEffect.Navigation.ToSignIn }
            }
        }
    }
}