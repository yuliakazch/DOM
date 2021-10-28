package com.dom.shared.util

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dom.R
import com.dom.shared.util.NavigationKeys.HOME
import com.dom.shared.util.NavigationKeys.PROFILE

@Composable
fun TitleAppbar(route: String) {
    Text(
        stringResource(
            id = when (route) {
                HOME    -> R.string.home_title
                PROFILE -> R.string.profile_title
                else    -> R.string.app_name
            }
        )
    )
}
