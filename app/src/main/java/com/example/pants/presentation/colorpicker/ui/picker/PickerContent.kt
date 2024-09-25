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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pants.data.repository.ColorRepositoryImpl
import com.example.pants.data.source.remote.ColorApiService
import com.example.pants.di.viewModelsModule
import com.example.pants.domain.model.ColorModel
import com.example.pants.domain.repository.ColorRepository
import com.example.pants.domain.usecase.CheckBoardOrderUseCase
import com.example.pants.domain.usecase.GetColorBoardUseCase
import com.example.pants.presentation.SharedGameViewModel
import com.example.pants.presentation.colorpicker.model.ColorPickerStateHolder
import com.example.pants.presentation.colorpicker.ui.preview.Previews
import com.example.pants.uikit.compose.animatedGradientTransition
import com.example.pants.uikit.hue
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

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
    PickerContent(
        stateHolder = ColorPickerStateHolder(viewModel = viewModel<SharedGameViewModel>()),
        onHueChange = { _ -> },
    )
}
