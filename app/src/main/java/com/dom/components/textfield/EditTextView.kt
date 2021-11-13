package com.dom.components.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@Composable
fun EditTextView(
	text: String,
	label: String,
	onTextChange: (String) -> Unit,
	imeAction: ImeAction,
) {
	OutlinedTextField(
		value = text,
		onValueChange = onTextChange,
		label = { Text(label) },
		singleLine = true,
		keyboardOptions = KeyboardOptions(imeAction = imeAction),
		modifier = Modifier.fillMaxWidth(),
	)
}