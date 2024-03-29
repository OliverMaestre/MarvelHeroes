package com.omaestre.data.models

import com.google.gson.annotations.SerializedName

data class Hero(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,

    @SerializedName("resourceURI")
    val url: String,

    @SerializedName("comics")
    val comics: Document,

    @SerializedName("series")
    val series: Document,

    @SerializedName("stories")
    val stories: Document

)