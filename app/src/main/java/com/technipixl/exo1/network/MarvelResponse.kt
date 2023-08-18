package com.technipixl.exo1.network

data class MarvelResponse(
    val data: DataResponse
)
data class DataResponse(
    val results: List<Character>
)
data class Thumbnail(
    val path: String,
    val extension: String)

data class Character(
    val id: String,
    val name: String,
    val thumbnail: Thumbnail
)