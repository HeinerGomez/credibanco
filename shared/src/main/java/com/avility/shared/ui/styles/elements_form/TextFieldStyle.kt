package com.avility.shared.ui.styles.elements_form

import androidx.compose.ui.graphics.Color
import com.avility.shared.ui.constants.MeasureSmallDimen
import com.avility.shared.ui.styles.DimensionParams

sealed class TextFieldStyle(
    open val dimensionParams: DimensionParams,
    open val containerColor: Color? = null,
    open val contentColor: Color? = null,
    open val fontSize: Float? = null
) {
    object Standard : TextFieldStyle(
        dimensionParams = DimensionParams(
            height = MeasureSmallDimen.DIMEN_X24.value
        ),
    )
}
