package com.avility.shared.ui.components.containers

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.avility.shared.ui.components.interaction_sources.NoRippleInteractionSource
import com.avility.shared.ui.constants.MeasureSmallDimen
import com.avility.shared.ui.constants.roundedShapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicContainer(
    modifier: Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    roundedCornerShape: CornerBasedShape = roundedShapes.medium,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    onTap: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {

    val interactionSource = if (onTap == null) {
        remember { NoRippleInteractionSource() }
    } else {
        remember { MutableInteractionSource() }
    }

    return Card(
        modifier = modifier,
        shape = roundedCornerShape,
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        onClick = onTap ?: {},
        interactionSource = interactionSource
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content()
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000,
    device = Devices.PIXEL_3A
)
fun BasicContainerPreview() {
    return BasicContainer(
        modifier = Modifier
            .fillMaxWidth()
            .height(
                MeasureSmallDimen.DIMEN_X36.value
            ),
        roundedCornerShape = roundedShapes.large,
        containerColor = Color.White
    ) {
        Text(text = "Hello World")
    }
}