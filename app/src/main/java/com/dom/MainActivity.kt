package com.dom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.dom.features.home.ui.HomeDestination
import com.dom.features.profile.ui.ProfileDestination
import com.dom.features.signin.ui.SignInDestination
import com.dom.features.signup.ui.SignUpDestination
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