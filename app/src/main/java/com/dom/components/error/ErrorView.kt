package com.dom.components.error

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dom.R

@Composable
fun ErrorView() {
	Box(
		modifier = Modifier.fillMaxSize(),
	) {
		Text(
			text = stringResource(R.string.error),
			modifier = Modifier.align(Alignment.Center)
		)
	}
}