package com.omaestre.marvel.domain.model

import com.google.gson.annotations.SerializedName

data class Document (
    @SerializedName("items")
    val items : List<Item>

    )
