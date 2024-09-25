package com.example.pants.presentation.colorpicker.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pants.domain.model.ColorModel
import com.example.pants.presentation.SharedGameViewModel
import kotlinx.coroutines.flow.map

@Stable
class ColorPickerStateHolder(
    private val viewModel: SharedGameViewModel,
) {

    @Composable
    fun collectSelectedColorAsState(): State<Color> =
        viewModel.selectedColor.collectAsStateWithLifecycle()

    @Composable
    fun collectCurrentColorNameAsState(): State<String> =
        viewModel.currentColorName
            .map { it.orEmpty() }
            .collectAsStateWithLifecycle(initialValue = "")

    @Composable
    fun collectColorBoardAsState(): State<List<ColorModel>> =
        viewModel.colorBoard.collectAsStateWithLifecycle()

}
