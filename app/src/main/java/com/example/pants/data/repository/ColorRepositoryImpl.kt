package com.example.pants.data.repository

import android.util.Log
import com.example.pants.data.source.remote.ColorApiService
import com.example.pants.data.mapper.toColorModel
import com.example.pants.domain.model.ColorModel
import com.example.pants.domain.repository.ColorRepository
import com.example.pants.data.source.local.generateRandomColor
import java.util.Locale

class ColorRepositoryImpl(
    private val apiService: ColorApiService,
) : ColorRepository {

    override suspend fun getSetOfUniqueColorsFromApiService(count: Int): Result<Set<ColorModel>> = runCatching {
        val colorList = mutableSetOf<ColorModel>()
        while(colorList.size < count) {
            val randomHSVColorValue = generateRandomColor()
            val randomColorValues = randomHSVColorValue.split(",")
            val randomColorSaturation = randomColorValues[1].toDouble()
            val randomColorBrightness = randomColorValues[2].toDouble()

            val checkIfColorIsAcceptableByBrightnessAndSaturation =
                (randomColorSaturation > 0.3) && (randomColorBrightness > 0.4)
            if (!checkIfColorIsAcceptableByBrightnessAndSaturation) continue

            val colorFromApiByRandomHSV = apiService.getColor(randomHSVColorValue).toColorModel()

            val colorIsInCommonColors =
                COMMON_USE_NAMES.contains(colorFromApiByRandomHSV.name.lowercase(Locale.getDefault()))

            if (colorIsInCommonColors) continue

            colorList.add(colorFromApiByRandomHSV)
        }
        colorList
    }

    private companion object {
        val COMMON_USE_NAMES = setOf(
            "beige",
            "black",
            "blue violet",
            "blue",
            "brown",
            "crimson",
            "cyan",
            "gold",
            "gray",
            "green",
            "indigo",
            "khaki",
            "lavender",
            "lime green",
            "magenta",
            "maroon",
            "navy blue",
            "olive",
            "orange",
            "pink",
            "plum",
            "purple",
            "red",
            "salmon",
            "silver",
            "sky blue",
            "teal",
            "violet",
            "white",
            "yellow",
        )
    }
}
