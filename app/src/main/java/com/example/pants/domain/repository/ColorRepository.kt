package com.example.pants.domain.repository

import com.example.pants.domain.model.ColorModel

interface ColorRepository {

    suspend fun getSetOfUniqueColorsFromApiService(count: Int): Result<Set<ColorModel>>

}
