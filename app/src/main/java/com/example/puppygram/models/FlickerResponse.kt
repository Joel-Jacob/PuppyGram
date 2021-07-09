package com.example.puppygram.models

import com.google.gson.annotations.SerializedName

data class FlickerResponse(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("description") val description: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("generator") val generator: String,
    @SerializedName("items") val items: MutableList<Items>
)

data class Items(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("media") val media: Media,
    @SerializedName("date_taken") val date_taken: String,
    @SerializedName("description") val description: String,
    @SerializedName("published") val published: String,
    @SerializedName("author") val author: String,
    @SerializedName("author_id") val author_id: String,
    @SerializedName("tags") val tags: String,
    var showDetails: Boolean = false
)

data class Media(
    @SerializedName("m") val m: String
)