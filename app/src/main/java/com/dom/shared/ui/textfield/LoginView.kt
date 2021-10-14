package com.dom.shared.ui.textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.dom.R

@Composable
fun LoginView(
    login: String,
    focusRequester: FocusRequester,
    onLoginChange: (String) -> Unit,
) {
    TextField(
        value = login,
        onValueChange = onLoginChange,
        label = { Text(stringResource(R.string.login)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusRequester.requestFocus() }
        ),
    )
}