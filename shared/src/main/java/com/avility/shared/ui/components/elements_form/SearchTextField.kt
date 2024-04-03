package com.avility.shared.ui.components.elements_form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.avility.shared.ui.constants.MeasureSmallDimen
import com.avility.shared.ui.constants.roundedShapes
import com.avility.shared.ui.styles.elements_form.TextFieldStyle

@Composable
fun SearchTextField(
    style: TextFieldStyle,
    placeholder: String = "",
    value: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChange: (value: String) -> Unit = {},
    onSearch: () -> Unit = {},
    onClearSearch: () -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(style.dimensionParams.height)
            .padding(MeasureSmallDimen.DIMEN_X00.value)
            .focusRequester(focusRequester),
        value = value,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.colors(
            focusedTextColor = style.contentColor ?: MaterialTheme.colorScheme.primaryContainer,
            focusedContainerColor = style.containerColor ?: MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = style.containerColor ?: MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = style.contentColor ?: MaterialTheme.colorScheme.primaryContainer,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.bodyMedium.run {
            style.fontSize?.let {
                copy(fontSize = TextUnit(it, TextUnitType.Sp))
            } ?: this
        },
        placeholder = {
            Text(
                text = placeholder,
                color = style.contentColor ?: MaterialTheme.colorScheme.primaryContainer,
                style = MaterialTheme.typography.bodyMedium.run {
                    style.fontSize?.let {
                        copy(fontSize = TextUnit(it, TextUnitType.Sp))
                    } ?: this
                }
            )
        },
        isError = false,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusRequester.freeFocus()
                onSearch()
                defaultKeyboardAction(ImeAction.Search)
            }
        ),
        singleLine = true,
        shape = roundedShapes.large,
        trailingIcon = {
            IconButton(
                onClick = {
                    focusRequester.requestFocus()
                    onClearSearch()
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "",
                    tint = style.contentColor ?: MaterialTheme.colorScheme.primaryContainer
                )
            }
        }
    )
}