package com.dom.features.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dom.R
import com.dom.features.splash.presentation.SplashEffect
import com.dom.shared.base.LAUNCH_LISTEN_FOR_EFFECTS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SplashScreen(
    effectFlow: Flow<SplashEffect>?,
    onNavigationRequested: (navigationEffect: SplashEffect.Navigation) -> Unit,
) {

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is SplashEffect.Navigation ->
                    onNavigationRequested(effect)
            }
        }?.collect()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 56.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_full_logo),
            contentDescription = null,
            modifier = Modifier,
        )
    }
}