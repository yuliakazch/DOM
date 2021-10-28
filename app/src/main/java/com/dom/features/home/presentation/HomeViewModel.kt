package com.dom.features.home.presentation

import com.dom.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :
    BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

    override fun setInitialState(): HomeState =
        HomeState(loading = false)

    override fun handleEvents(event: HomeEvent) {

    }
}