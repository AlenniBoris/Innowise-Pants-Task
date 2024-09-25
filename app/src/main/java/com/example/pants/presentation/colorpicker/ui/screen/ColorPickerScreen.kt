package com.example.pants.presentation.colorpicker.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pants.domain.model.ColorModel
import com.example.pants.R
import com.example.pants.presentation.SharedGameViewModel
import com.example.pants.presentation.colorpicker.model.ColorPickerStateHolder
import com.example.pants.presentation.colorpicker.ui.picker.PickerContent
import com.example.pants.uikit.hue


@Composable
fun ColorPickerScreen(
    stateHolder: ColorPickerStateHolder,
    onSave: () -> Unit,
    onUpdateColorSettings: (Float) -> Unit,
) {
    ColorPicker(
        stateHolder = stateHolder,
        onSaveColor = onSave,
        onUpdateColorSettings = onUpdateColorSettings,
    )
}

@Composable
private fun ColorPicker(
    stateHolder: ColorPickerStateHolder,
    onUpdateColorSettings: (Float) -> Unit,
    onSaveColor: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.main_color)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header(stateHolder)

        PickerContent(
            stateHolder = stateHolder,
            onHueChange = onUpdateColorSettings,
        )

        SaveButton(
            onClick = onSaveColor,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun Header(stateHolder: ColorPickerStateHolder) {
    val name by stateHolder.collectCurrentColorNameAsState()

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp, start = 16.dp, end = 16.dp),
        text = name,
        style = TextStyle(
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    )
}


@Preview(showBackground = true)
@Composable
private fun ColorPickerPreview() {
    val model = ColorModel(
        name = "Color of your pants on fire on saturday morning",
        realHue = 227.0f,
        saturation = 1.0f,
        value = 1.0f,
        guessHue = null,
    )
    val selected = Color.hsv(model.realHue, model.saturation, model.value)
//    ColorPicker(selectedColor = selected,
//        colorName = model.name,
//        onUpdateColorSettings = { _ -> },
//        onSaveColor = {},
//        colors = List(5) { model },
//    )
}
