package com.technipixl.exo1.network.comicsDetail

data class ComicsDetailResponse(
    val data: ComicsDataResponse
)
data class ComicsDataResponse(
    val results: List<ComicsCharacter>
)
data class ComicsCharacter(
    val title: String,
    val description: String,
    val images: List<ComicsThumbnail>
)
data class ComicsThumbnail(
    val path: String,
    val extension: String)
