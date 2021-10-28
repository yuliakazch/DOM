package com.dom.features.profile.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.dom.R

@Composable
fun ProfileDestination(navController: NavHostController) {
    Text(stringResource(R.string.profile_title))
}