package com.avility.shared.ui.components.others

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.avility.shared.R

@Composable
fun LottieProgressBar() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.5f
    )

    return LottieAnimation(
        composition,
        progress,
    )
}

@Composable
@Preview(
    showBackground = true,
    widthDp = 500,
    heightDp = 500
)
fun LottieProgressBarPreview() {
    LottieProgressBar()
}