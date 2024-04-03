package com.avility.shared.ui.components.others

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.avility.shared.ui.constants.MeasureLargeDimen
import com.avility.shared.ui.constants.MeasureSmallDimen

@Composable
fun LottieInfoScreen(
    @RawRes resource: Int,
    message: String,
    contentColor: Color = MaterialTheme.colorScheme.secondary
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resource))
    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = true,
        iterations = 1,
        speed = 1f
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MeasureSmallDimen.DIMEN_X03.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier.height(MeasureLargeDimen.DIMEN_X20.value),
            composition = composition,
            progress = progress,
        )
        Spacer(modifier = Modifier.height(
            MeasureSmallDimen.DIMEN_X10.value
        ))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = contentColor
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}