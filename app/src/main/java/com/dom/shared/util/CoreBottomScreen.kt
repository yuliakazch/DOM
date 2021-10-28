package com.dom.shared.util

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.dom.R
import com.dom.shared.util.NavigationKeys.HOME_ROUTE
import com.dom.shared.util.NavigationKeys.PROFILE_ROUTE

sealed class CoreBottomScreen(
    val route: String,
    @StringRes val stringId: Int,
    val image: ImageVector,
) {

    object Home : CoreBottomScreen(HOME_ROUTE, R.string.home_title, Icons.Filled.Home)
    object Profile : CoreBottomScreen(PROFILE_ROUTE, R.string.profile_title, Icons.Filled.Person)
}