package com.example.pants.data.source.remote.model

import java.io.Serializable

data class ColorResponse(
    val name: Name,
    val hsv: Hsv
) : Serializable

data class Name(val value: String): Serializable

data class Hsv(val fraction: Fraction) : Serializable {
    data class Fraction(val h: Float, val s: Float, val v: Float) : Serializable
}
