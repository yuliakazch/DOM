package com.dom.components.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun TopBarFullView(
    title: String,
    oneIcon: ImageVector,
    twoIcon: ImageVector,
    onOneIconClicked: () -> Unit,
    onTwoIconClicked: () -> Unit,
    onBackIconClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable { onBackIconClicked() },
            )
        },
        actions = {
            Icon(
                imageVector = oneIcon,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable { onOneIconClicked() },
            )
            Icon(
                imageVector = twoIcon,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable { onTwoIconClicked() },
            )
        },
    )
}