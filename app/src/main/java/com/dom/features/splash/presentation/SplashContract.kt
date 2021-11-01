package com.dom.features.splash.presentation

import com.dom.shared.base.ViewEvent
import com.dom.shared.base.ViewSideEffect
import com.dom.shared.base.ViewState

object SplashEvent : ViewEvent

object SplashState : ViewState

sealed class SplashEffect : ViewSideEffect {

    sealed class Navigation : SplashEffect() {

        object ToAuthorization : Navigation()

        object ToHome : Navigation()
    }
}