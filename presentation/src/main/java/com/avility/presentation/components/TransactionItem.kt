package com.avility.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.avility.domain.model.TransactionModel
import com.avility.presentation.R
import com.avility.shared.extensions.toFormatCurrency
import com.avility.shared.ui.components.containers.BaseRowItem
import com.avility.shared.ui.constants.MeasureSmallDimen

@Composable
fun TransactionItem(
    transaction: TransactionModel,
    onAction: () -> Unit
) {
    BaseRowItem(
        leading = {
            Column(Modifier.padding(MeasureSmallDimen.DIMEN_X04.value)) {
                Text(
                    text = stringResource(R.string.recipe_number),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = transaction.recipeId.orEmpty().ifEmpty {
                        stringResource(R.string.without_recipe_id)
                    },
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = transaction.amount.toFormatCurrency(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        trailing = {
            Spacer(
                modifier = Modifier
                    .width(MeasureSmallDimen.DIMEN_X04.value)
                    .fillMaxHeight()
                    .background(Color(transaction.indStatus.color))
            )
        },
        leadingWeight = 0.9f,
        trailingWeight = 0.1f,
        containerColor = MaterialTheme.colorScheme.secondary,
        withHorizontalPadding = false,
        withVerticalPadding = false,
        onTap = onAction
    )
}