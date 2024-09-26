package com.example.pants.presentation.colorpicker.ui.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pants.R
import com.example.pants.uikit.hue

@Composable
internal fun ColorDetails(modifier: Modifier, color: Color) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        DataPointPresenter("HUE", stringResource(R.string.hue_data, color.hue))
        Spacer(modifier = Modifier.height(10.dp))
        DataPointPresenter(
            "RGB",
            String.format(stringResource(R.string.rgb_data), color.red, color.green, color.blue)
        )
    }
}

@Composable
internal fun DataPointPresenter(title: String, data: String) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(
            text = "$title: $data",
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
        )
    }
}
