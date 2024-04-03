package com.avility.shared.ui.components.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.avility.shared.ui.components.others.LottieProgressBar
import com.avility.shared.ui.constants.MeasureSmallDimen

@Composable
fun MainContainer(
    isLoading: Boolean,
    header: (@Composable () -> Unit)? = null,
    headerPadding: Boolean = true,
    headerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            header?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(headerColor)
                        .padding(
                            if (headerPadding) {
                                MeasureSmallDimen.DIMEN_X10.value
                            } else {
                                MeasureSmallDimen.DIMEN_X00.value
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    it()
                }
            }
            Spacer(Modifier.height(MeasureSmallDimen.DIMEN_X04.value))
            Box(modifier = Modifier.padding(MeasureSmallDimen.DIMEN_X10.value)) {
                content()
            }
        }
        if (isLoading) {
            LottieProgressBar()
        }
    }
}