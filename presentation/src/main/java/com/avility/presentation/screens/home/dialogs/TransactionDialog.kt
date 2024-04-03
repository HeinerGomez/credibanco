package com.avility.presentation.screens.home.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.avility.presentation.R
import com.avility.presentation.models_ui.TransactionFormUI
import com.avility.shared.ui.constants.MeasureSmallDimen

@Composable
fun TransactionDialog(
    isForCreate: Boolean = true,
    canAnnul: Boolean = false,
    transactionUI: TransactionFormUI = TransactionFormUI(),
    onAction: (TransactionFormUI) -> Unit,
    onDismiss: () -> Unit
) {

    var transaction by remember { mutableStateOf(transactionUI) }

    Dialog(
        onDismissRequest = {
           onDismiss()
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(0.97f),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = MeasureSmallDimen.DIMEN_X06.value
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MeasureSmallDimen.DIMEN_X02.value)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(0.98f),
                    value = transaction.commerceCode,
                    onValueChange = {
                        transaction = transaction.copy(
                            commerceCode = it
                        )
                    },
                    placeholder = {
                        Text(stringResource(R.string.commerce_code_label))
                    },
                    label = {
                        Text(stringResource(R.string.commerce_code_label))
                    },
                    enabled = isForCreate
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X04.value))
                TextField(
                    modifier = Modifier.fillMaxWidth(0.98f),
                    value = transaction.terminalCode,
                    onValueChange = {
                        transaction = transaction.copy(
                            terminalCode = it
                        )
                    },
                    placeholder = {
                        Text(stringResource(R.string.terminal_code_label))
                    },
                    label = {
                        Text(stringResource(R.string.terminal_code_label))
                    },
                    enabled = isForCreate
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X04.value))
                TextField(
                    modifier = Modifier.fillMaxWidth(0.98f),
                    value = transaction.amount,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = {
                        transaction = transaction.copy(
                            amount = it
                        )
                    },
                    placeholder = {
                        Text(stringResource(R.string.transaction_amount_label))
                    },
                    label = {
                        Text(stringResource(R.string.transaction_amount_label))
                    },
                    enabled = isForCreate
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X04.value))
                TextField(
                    modifier = Modifier.fillMaxWidth(0.98f),
                    value = transaction.card,
                    onValueChange = {
                        transaction = transaction.copy(
                            card = it
                        )
                    },
                    placeholder = {
                        Text(stringResource(R.string.card_associated_label))
                    },
                    label = {
                        Text(stringResource(R.string.card_associated_label))
                    },
                    enabled = isForCreate
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X04.value))
                if (isForCreate || canAnnul) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        ),
                        onClick = {
                            onAction(transaction)
                        }
                    ) {
                        if (isForCreate) {
                            Text(stringResource(R.string.btn_create_transaction))
                        } else {
                            Text(stringResource(R.string.btn_annul_transaction))
                        }
                    }
                }
            }
        }
    }
}