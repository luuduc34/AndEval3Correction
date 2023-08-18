package com.technipixl.exo1.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelServices {
    @GET("characters")
    suspend fun characterList(
        @Query("apikey", encoded = true) apiKey: String,
        @Query("ts", encoded = true) ts: String,
        @Query("hash", encoded = true) hash: String,
        @Query("limit", encoded = true) limit: String
    ): Response<MarvelResponse>

    @GET("characters/{id}")
    suspend fun characterDetailList(
        @Path("id", encoded = true) id:Long,
        @Query("apikey", encoded = true) apiKey: String,
        @Query("ts", encoded = true) ts: String,
        @Query("hash", encoded = true) hash: String
    ): Response<MarvelDetailResponse>
}
