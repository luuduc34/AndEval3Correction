package com.technipixl.exo1.network

import com.technipixl.exo1.HashGenerator
import com.technipixl.exo1.network.character.MarvelResponse
import com.technipixl.exo1.network.comics.MarvelDetailResponse
import com.technipixl.exo1.network.comicsDetail.ComicsDetailResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import java.util.concurrent.TimeUnit

class MarvelServiceImpl {

    val timeStamp = Date().time
    val privateKey = "9c0693512db2baed250778f5b5fe87f22bd03211"
    val publicKey = "e56e66cc068a53647f3a85b2f9bbed37"
    val hashExample = HashGenerator.generateHash(timeStamp, privateKey, publicKey)
    val limit: String = "100"
    fun getRetrofit(): Retrofit {
        val okBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(15, TimeUnit.SECONDS)
            callTimeout(15, TimeUnit.SECONDS)
            readTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
        }
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okBuilder.build())
            .build()
    }
    suspend fun getCharacter(): Response<MarvelResponse> = getRetrofit().create(MarvelServices::class.java).characterList(publicKey, timeStamp.toString(), hashExample.toString(), limit)
    suspend fun getCharacterDetail(id: Long): Response<MarvelDetailResponse> = getRetrofit().create(
        MarvelServices::class.java).characterDetailList(id, publicKey, timeStamp.toString(), hashExample.toString())
    suspend fun getComicsCharacterDetail(id: String): Response<ComicsDetailResponse> = getRetrofit().create(MarvelServices::class.java).comicsDetail(id, publicKey, timeStamp.toString(), hashExample.toString())
}