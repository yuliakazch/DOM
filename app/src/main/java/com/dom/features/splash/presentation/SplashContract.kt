package com.dom.features.splash.presentation

import com.dom.core.ViewEvent
import com.dom.core.ViewSideEffect
import com.dom.core.ViewState

object SplashEvent : ViewEvent

object SplashState : ViewState

sealed class SplashEffect : ViewSideEffect {

	sealed class Navigation : SplashEffect() {

		object ToAuthorization : Navigation()

		object ToHome : Navigation()
	}
}