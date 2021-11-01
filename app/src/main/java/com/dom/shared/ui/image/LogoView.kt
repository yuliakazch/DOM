package com.dom.shared.ui.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dom.R

@Composable
fun LogoView() {
    Image(
        painter = painterResource(R.drawable.ic_cat_logo),
        contentDescription = null,
        modifier = Modifier.size(width = 140.dp, height = 180.dp),
    )
}