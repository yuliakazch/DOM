package com.dom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.dom.components.theme.DOMTheme
import com.dom.features.home.ui.HomeDestination
import com.dom.features.profile.ui.ProfileDestination
import com.dom.features.signin.ui.SignInDestination
import com.dom.features.signup.ui.SignUpDestination
import com.dom.features.splash.ui.SplashDestination
import com.dom.core.CoreBottomScreen
import com.dom.core.NavigationKeys.FOLDER_DETAIL
import com.dom.core.NavigationKeys.HOME
import com.dom.core.NavigationKeys.PROFILE
import com.dom.core.NavigationKeys.SIGN_IN
import com.dom.core.NavigationKeys.SIGN_UP
import com.dom.core.NavigationKeys.SPLASH
import com.dom.core.NavigationKeys.SUBJECT_CREATE
import com.dom.core.NavigationKeys.SUBJECT_DETAIL
import com.dom.core.NavigationKeys.SUBJECT_EDIT
import com.dom.features.folder.detail.ui.FolderDetailDestination
import com.dom.features.subject.create.ui.SubjectCreateDestination
import com.dom.features.subject.detail.ui.SubjectDetailDestination
import com.dom.features.subject.edit.ui.SubjectEditDestination
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
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

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun DOMApp() {
    DOMTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val currentRoute = currentDestination?.route

        val coreScreens = listOf(
            CoreBottomScreen.Home,
            CoreBottomScreen.Profile,
        )

        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                bottomBar = {
                    if (currentRoute == HOME || currentRoute == PROFILE) {
                        BottomNavigation(
                            backgroundColor = MaterialTheme.colors.background,
                        ) {
                            coreScreens.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(screen.image, contentDescription = null) },
                                    label = { Text(stringResource(screen.stringId)) },
                                    selected = currentDestination.hierarchy.any { it.route == screen.route },
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
                NavHost(navController = navController, startDestination = SPLASH) {
                    composable(SPLASH) { SplashDestination(navController) }
                    composable(SIGN_IN) { SignInDestination(navController) }
                    composable(SIGN_UP) { SignUpDestination(navController) }
                    navigation(
                        startDestination = HOME,
                        route = CoreBottomScreen.Home.route,
                    ) {
                        composable(HOME) { HomeDestination(navController) }

                        composable(
                            route = "$FOLDER_DETAIL/{folderId}",
                            arguments = listOf(navArgument("folderId") { type = NavType.IntType }),
                        ) {
                            FolderDetailDestination(navController)
                        }

                        composable(
                            route = "$SUBJECT_DETAIL/{folderId}/{subjectId}",
                            arguments = listOf(
                                navArgument("folderId") { type = NavType.IntType },
                                navArgument("subjectId") { type = NavType.IntType },
                            ),
                        ) {
                            SubjectDetailDestination(navController)
                        }

                        composable(
                            route = "$SUBJECT_EDIT/{folderId}/{subjectId}",
                            arguments = listOf(
                                navArgument("folderId") { type = NavType.IntType },
                                navArgument("subjectId") { type = NavType.IntType },
                            ),
                        ) {
                            SubjectEditDestination(navController)
                        }

                        composable(
                            route = "$SUBJECT_CREATE/{folderId}",
                            arguments = listOf(navArgument("folderId") { type = NavType.IntType }),
                        ) {
                            SubjectCreateDestination(navController)
                        }
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