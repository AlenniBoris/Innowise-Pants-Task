package com.example.pants.data.source.remote

import com.example.pants.data.source.remote.model.ColorResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ColorApiService {
    @GET("id")
    suspend fun getColor(
        @Query("hsv") hsv: String
    ): ColorResponse
}
