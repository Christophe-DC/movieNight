package com.cdcoding.movienight.login.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp


@Composable
fun InputEditText(
    text: String,
    label: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    labelStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxWidth())
        {
            Text(
                text = label,
                style = labelStyle,
                color = MaterialTheme.colors.onSurface
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
        ) {
            BasicTextField(
                modifier = Modifier
                    .border(
                        width = 0.5.dp,
                        color = MaterialTheme.colors.onSurface,
                        shape = shapes.small
                    )
                    .clip(shape = shapes.small)
                    .onFocusEvent(onFocusChange),
                value = text,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                textStyle = textStyle.copy(color = MaterialTheme.colors.onSurface),
                singleLine = singleLine,
                onValueChange = onValueChange,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .background(color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f))
                            .fillMaxWidth()
                    )
                    {
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp, start = 16.dp, bottom = 10.dp)
                                .fillMaxWidth(),
                        ) {
                            if (isHintVisible) {
                                Text(
                                    modifier = Modifier.alpha(0.4f),
                                    text = hint,
                                    style = textStyle,
                                    color = MaterialTheme.colors.onSurface
                                )
                            }
                            innerTextField()
                        }
                    }
                }
            )
        }
    }
}