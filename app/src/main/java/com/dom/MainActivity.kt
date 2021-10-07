package com.dom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.dom.features.signin.ui.SignInView
import com.dom.ui.theme.DOMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DOMTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SignInView()
                }
            }
        }
    }
}