package com.example.pants.presentation.colorpicker.ui.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pants.domain.model.ColorModel
import com.example.pants.presentation.SharedGameViewModel
import com.example.pants.presentation.colorpicker.model.ColorPickerStateHolder

@Composable
internal fun Previews(
    modifier: Modifier = Modifier,
    stateHolder: ColorPickerStateHolder,
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ColorBoardPreview(modifier = Modifier.fillMaxHeight(), stateHolder = stateHolder)

        ColorPreview(stateHolder = stateHolder)
    }
}

@Preview
@Composable
fun PreviewsPreview() {
    val model = ColorModel(
        name = "Color of your pants on fire on saturday morning",
        realHue = 227.0f,
        saturation = 1.0f,
        value = 1.0f,
        guessHue = null,
    )

    Previews(
        modifier = Modifier,
        stateHolder = ColorPickerStateHolder(viewModel = viewModel<SharedGameViewModel>()),
    )
}
