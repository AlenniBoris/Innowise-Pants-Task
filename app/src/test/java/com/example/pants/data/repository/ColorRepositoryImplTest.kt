package com.example.pants.data.repository

import com.example.pants.data.source.remote.ColorApiService
import com.example.pants.data.source.remote.model.ColorResponse
import com.example.pants.data.source.remote.model.Hsv
import com.example.pants.data.source.remote.model.Name
import com.example.pants.domain.model.ColorModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class ColorRepositoryImplTest {

    private lateinit var repositoryImpl: ColorRepositoryImpl
    private val apiService: ColorApiService = mockk()


    @Before
    fun setUp() {
        val testDispatcher = UnconfinedTestDispatcher()
        repositoryImpl = ColorRepositoryImpl(apiService, testDispatcher, testDispatcher)
    }

    @Test
    fun `getSetOfUniqueColorsFromApiService returns a set of ColorModel`() = runTest {
        mockApiResponse { (h, s, v) ->
            ColorResponse(
                name = Name(""),
                hsv = Hsv(fraction = Hsv.Fraction(h, s, v))
            )
        }

        val sizes = listOf(1, 2, 3, 4, 5, 0)
        val resList: MutableList<Result<Set<ColorModel>>> = mutableListOf()

        for (size in sizes) {
            val result = repositoryImpl.getSetOfUniqueColorsFromApiService(size)
            //Asserts
            resList.add(result)
        }

        for (result in resList) {
            assertTrue(result.isSuccess)
        }
    }

    @Test
    fun `getSetOfUniqueColorsFromApiService returns a set of ColorModel of exact size`() = runTest {
        mockApiResponse { (h, s, v) ->
            ColorResponse(
                name = Name(""),
                hsv = Hsv(fraction = Hsv.Fraction(h, s, v))
            )
        }

        val sizes = listOf(1, 2, 3, 4, 5, 0)
        val resList: MutableList<Boolean> = mutableListOf()

        for (size in sizes) {
            val result = repositoryImpl.getSetOfUniqueColorsFromApiService(size)
            //Asserts
            resList.add(result.getOrNull()?.size == size)
        }

        for (res in resList) {
            assertEquals(res, true)
        }
    }

    @Test
    fun `getSetOfUniqueColorsFromApiService returns a set of ColorModel without names from common`() =
        runTest {
            mockApiResponse { (h, s, v) ->
                ColorResponse(
                    name = Name(""),
                    hsv = Hsv(fraction = Hsv.Fraction(h, s, v))
                )
            }

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

            val sizes = listOf(1, 2, 3, 4, 5, 0)
            val resList: MutableList<MutableList<String>> = mutableListOf()

            for (size in sizes) {
                val result = repositoryImpl.getSetOfUniqueColorsFromApiService(size)
                val names: MutableList<String> = mutableListOf()
                for (element in result.getOrNull()!!) {
                    names.add(element.name)
                }
                //Asserts
                resList.add(names)
            }

            for (namesList in resList) {
                for (name in namesList) {
                    assertTrue(!COMMON_USE_NAMES.contains(name))
                }
            }
        }

    @Test
    fun `getSetOfUniqueColorsFromApiService returns a set of ColorModel with proper value and saturation`() =
        runTest {
            mockApiResponse { (h, s, v) ->
                ColorResponse(
                    name = Name(""),
                    hsv = Hsv(fraction = Hsv.Fraction(h, s, v))
                )
            }

            val sizes = listOf(1, 2, 3, 4, 5, 0)
            val resList: MutableList<MutableList<Pair<Float, Float>>> = mutableListOf()

            for (size in sizes) {
                val result = repositoryImpl.getSetOfUniqueColorsFromApiService(size)
                val names: MutableList<Pair<Float, Float>> = mutableListOf()
                for (element in result.getOrNull()!!) {
                    names.add(Pair(element.saturation, element.value))
                }
                //Asserts
                resList.add(names)
            }

            for (namesList in resList) {
                for (name in namesList) {
                    assertTrue(name.first > 0.3 && name.second > 0.4)
                }
            }
        }


    private fun mockApiResponse(returnedValue: (hsv: List<Float>) -> ColorResponse) {
        coEvery { apiService.getColor(any()) }.answers {
            val hsv = it.invocation.args.first().toString().split(",").map { it.toFloat() }
            returnedValue(hsv)
        }
    }
}