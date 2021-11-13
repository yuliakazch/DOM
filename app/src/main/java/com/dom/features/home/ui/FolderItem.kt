package com.dom.features.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dom.R

@Composable
fun FolderItem(
	name: String?,
	image: Painter?,
) {
	Card(
		modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
		elevation = 4.dp,
		shape = RoundedCornerShape(corner = CornerSize(16.dp))
	) {
		Column(
			modifier = Modifier
				.padding(16.dp)
				.fillMaxWidth(),
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			Text(
				text = name ?: stringResource(R.string.new_folder),
				style = MaterialTheme.typography.h6,
				modifier = Modifier.fillMaxWidth(),
				textAlign = TextAlign.Center,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis,
			)
			Image(
				painter = image ?: painterResource(R.drawable.ic_box),
				contentDescription = null,
				modifier = Modifier
					.size(128.dp)
					.padding(top = 16.dp),
			)
		}
	}
}