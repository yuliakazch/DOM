package com.dom.features.splash.presentation

import androidx.lifecycle.viewModelScope
import com.dom.shared.base.BaseViewModel
import com.dom.shared.logout.domain.usecase.IsTokenExistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isTokenExistUseCase: IsTokenExistUseCase,
)
    : BaseViewModel<SplashEvent, SplashState, SplashEffect>() {

    override fun setInitialState(): SplashState = SplashState

    override fun handleEvents(event: SplashEvent) {}

    init {
        loadApp()
    }

    private fun loadApp() {
        viewModelScope.launch {
            delay(2_000)

            if (isTokenExistUseCase()) {
                setEffect { SplashEffect.Navigation.ToHome }
            } else {
                setEffect { SplashEffect.Navigation.ToAuthorization }
            }
        }
    }
}