package com.example.moviedatabase.data.remote.response

import com.example.moviedatabase.data.model.ItemEntity
import com.google.gson.annotations.SerializedName

data class SearchRepoResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<ItemEntity>
)