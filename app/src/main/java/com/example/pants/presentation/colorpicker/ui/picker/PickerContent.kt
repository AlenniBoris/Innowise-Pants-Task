package com.example.pants.presentation.colorpicker.ui.picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pants.domain.model.ColorModel
import com.example.pants.presentation.colorpicker.model.ColorPickerStateHolder
import com.example.pants.presentation.colorpicker.ui.preview.Previews
import com.example.pants.uikit.compose.animatedGradientTransition
import com.example.pants.uikit.hue

@Composable
internal fun PickerContent(
    stateHolder: ColorPickerStateHolder,
    onHueChange: (Float) -> Unit,
) {
    Column(
        modifier = Modifier.width(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(48.dp),
    ) {
        Previews(
            modifier = Modifier.fillMaxWidth(),
            stateHolder = stateHolder,
        )

        HuePicker(stateHolder = stateHolder, onHueChange = onHueChange)
    }
}

@Preview
@Composable
fun PickerContentPreview() {
    val model = ColorModel(
        name = "Color of your pants on fire on saturday morning",
        realHue = 227.0f,
        saturation = 1.0f,
        value = 1.0f,
        guessHue = null,
    )

//    PickerContent(
//        selectedColor = Color.Yellow,
//        onHueChange = { _ -> },
//        colors = List(5) { model }
//    )
}
