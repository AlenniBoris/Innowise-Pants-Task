package com.example.pants.data.source.remote.model

import java.io.Serializable

data class ColorResponse(
    val name: Name,
    val hsv: Hsv
) : Serializable

