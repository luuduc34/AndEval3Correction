package com.technipixl.exo1.network.comics

data class MarvelDetailResponse (
    val data: DataDetailResponse
)
data class DataDetailResponse(
    val results: List<CharacterDetail>
)
data class CharacterDetail(
    val id: String,
    val name: String,
    val thumbnail: ThumbnailDetail,
    val comics: Items
)
data class ThumbnailDetail(
    val path: String,
    val extension: String
)

data class  Items(
    val items: List<ItemList>
)

data class  ItemList(
    val resourceURI: String,
    val name: String
)