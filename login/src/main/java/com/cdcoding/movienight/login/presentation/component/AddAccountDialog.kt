package com.cdcoding.movienight.login.presentation.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.cdcoding.movienight.login.R
import com.cdcoding.movienight.login.presentation.InputEditTextState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddAccountDialog(
    onDismissRequest: () -> Unit,
    firstNameState: InputEditTextState,
    onFirstNameChange: (String) -> Unit,
    onFirstNameFocusChange: (FocusState) -> Unit,
    lastNameState: InputEditTextState,
    onLastNameChange: (String) -> Unit,
    onLastNameFocusChange: (FocusState) -> Unit,
    pseudoState: InputEditTextState,
    onPseudoChange: (String) -> Unit,
    onPseudoFocusChange: (FocusState) -> Unit,
    onValidClick: () -> Unit,
    isNewAccountCreating: Boolean = false
) {
    Dialog(
        onDismissRequest =  onDismissRequest
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .wrapContentHeight()
                .animateContentSize(),
        ) {
            Column(modifier = Modifier.fillMaxWidth())
            {
                Row(
                    modifier = Modifier
                        .padding(end = 10.dp, top = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        IconButton(
                            onClick = onDismissRequest,
                            modifier = Modifier
                                .then(Modifier.size(28.dp))
                                .clip(CircleShape)
                                .background(MaterialTheme.colors.onSurface)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                tint = MaterialTheme.colors.onBackground,
                                contentDescription = null
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.add_account_dialog_title),
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                if (isNewAccountCreating) {
                    Row(
                        modifier = Modifier
                            .padding(
                                top = 32.dp,
                                start = 24.dp,
                                end = 24.dp,
                                bottom = 22.dp
                            )
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .padding(top = 32.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth()
                    ) {
                        InputEditText(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = firstNameState.text,
                            label = stringResource(id = R.string.first_name),
                            hint = firstNameState.hint,
                            isHintVisible = firstNameState.isHintVisible,
                            onValueChange = onFirstNameChange,
                            onFocusChange = onFirstNameFocusChange,
                            singleLine = true,
                            textStyle = MaterialTheme.typography.body1,
                            labelStyle = MaterialTheme.typography.body2,
                        )

                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth()
                    ) {
                        InputEditText(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = lastNameState.text,
                            label = stringResource(id = R.string.last_name),
                            hint = lastNameState.hint,
                            isHintVisible = lastNameState.isHintVisible,
                            onValueChange = onLastNameChange,
                            onFocusChange = onLastNameFocusChange,
                            singleLine = true,
                            textStyle = MaterialTheme.typography.body1,
                            labelStyle = MaterialTheme.typography.body2
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth()
                    ) {
                        InputEditText(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = pseudoState.text,
                            label = stringResource(id = R.string.pseudo),
                            hint = pseudoState.hint,
                            isHintVisible = pseudoState.isHintVisible,
                            onValueChange = onPseudoChange,
                            onFocusChange = onPseudoFocusChange,
                            singleLine = true,
                            textStyle = MaterialTheme.typography.body1,
                            labelStyle = MaterialTheme.typography.body2,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(
                                top = 48.dp,
                                start = 24.dp,
                                end = 24.dp,
                                bottom = 18.dp
                            )
                            .fillMaxWidth()
                    ) {
                        val isValidButtonEnable = firstNameState.text.isNotBlank() &&
                                lastNameState.text.isNotBlank() &&
                                pseudoState.text.isNotBlank()
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = onValidClick,
                            enabled = isValidButtonEnable
                        ) {
                            Text(text = stringResource(id = R.string.valid))
                        }
                    }
                }
            }
        }
    }
}