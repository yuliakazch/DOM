package com.dom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.dom.features.home.ui.HomeDestination
import com.dom.features.profile.ui.ProfileDestination
import com.dom.features.signin.presentation.SignInEffect
import com.dom.features.signin.presentation.SignInViewModel
import com.dom.features.signin.ui.SignInScreen
import com.dom.features.signup.presentation.SignUpEffect
import com.dom.features.signup.presentation.SignUpViewModel
import com.dom.features.signup.ui.SignUpScreen
import com.dom.shared.util.CoreBottomScreen
import com.dom.shared.ui.theme.DOMTheme
import com.dom.shared.util.NavigationKeys.HOME
import com.dom.shared.util.NavigationKeys.PROFILE
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
        val navController = rememberNavController()
        val coreScreens = listOf(
            CoreBottomScreen.Home,
            CoreBottomScreen.Profile,
        )

        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                bottomBar = {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    val currentRoute = currentDestination?.route

                    if (currentRoute != SIGN_IN && currentRoute != SIGN_UP) {
                        BottomNavigation {
                            coreScreens.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(screen.image, contentDescription = null) },
                                    label = { Text(stringResource(screen.stringId)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            ) {
                NavHost(navController = navController, startDestination = SIGN_IN) {
                    composable(SIGN_IN) { SignInDestination(navController) }
                    composable(SIGN_UP) { SignUpDestination(navController) }
                    navigation(
                        startDestination = HOME,
                        route = CoreBottomScreen.Home.route,
                    ) {
                        composable(HOME) { HomeDestination(navController) }
                    }
                    navigation(
                        startDestination = PROFILE,
                        route = CoreBottomScreen.Profile.route,
                    ) {
                        composable(PROFILE) { ProfileDestination(navController) }
                    }
                }
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