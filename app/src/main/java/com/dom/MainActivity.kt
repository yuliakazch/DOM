package com.dom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dom.features.home.ui.HomeView
import com.dom.features.signin.ui.SignInView
import com.dom.features.signup.ui.SignUpView
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
            DOMTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = SIGN_IN) {
                        composable(SIGN_IN) { SignInView(navController) }
                        composable(SIGN_UP) { SignUpView(navController) }
                        composable(HOME) { HomeView() }
                    }
                }
            }
        }
    }
}