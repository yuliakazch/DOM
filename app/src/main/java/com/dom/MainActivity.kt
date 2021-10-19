package com.dom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dom.features.home.ui.HomeView
import com.dom.features.signin.presentation.SignInEffect
import com.dom.features.signin.presentation.SignInViewModel
import com.dom.features.signin.ui.SignInScreen
import com.dom.features.signup.presentation.SignUpEffect
import com.dom.features.signup.presentation.SignUpViewModel
import com.dom.features.signup.ui.SignUpScreen
import com.dom.shared.ui.theme.DOMTheme
import com.dom.shared.util.NavigationKeys.HOME
import com.dom.shared.util.NavigationKeys.SIGN_IN
import com.dom.shared.util.NavigationKeys.SIGN_UP
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DOMApp()
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun DOMApp() {
    DOMTheme {
        Surface(color = MaterialTheme.colors.background) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = SIGN_IN) {
                composable(SIGN_IN) { SignInDestination(navController) }
                composable(SIGN_UP) { SignUpDestination(navController) }
                composable(HOME) { HomeView() }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun SignInDestination(navController: NavHostController) {
    val viewModel: SignInViewModel = hiltViewModel()
    val state = viewModel.viewState.collectAsState().value
    SignInScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is SignInEffect.Navigation.ToHome -> {
                    navController.navigate(HOME) {
                        popUpTo(SIGN_IN) { inclusive = true }
                    }
                }

                is SignInEffect.Navigation.ToRegistration -> navController.navigate(SIGN_UP)
            }
        }
    )
}

@ExperimentalComposeUiApi
@Composable
fun SignUpDestination(navController: NavHostController) {
    val viewModel: SignUpViewModel = hiltViewModel()
    val state = viewModel.viewState.collectAsState().value
    SignUpScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is SignUpEffect.Navigation.ToAuthorization -> {
                    navController.popBackStack()
                }
            }
        }
    )
}