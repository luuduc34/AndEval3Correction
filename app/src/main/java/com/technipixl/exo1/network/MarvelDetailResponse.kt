package com.technipixl.exo1.network

data class MarvelDetailResponse (
    val data: DataDetailResponse
)
data class DataDetailResponse(
    val results: List<CharacterDetail>
)
data class ThumbnailDetail(
    val path: String,
    val extension: String)
data class CharacterDetail(
    val id: String,
    val name: String,
    val thumbnail: ThumbnailDetail,
    val comics: List<Items>
)

data class  Items(
    val resourceURI: String,
    val name: String
)