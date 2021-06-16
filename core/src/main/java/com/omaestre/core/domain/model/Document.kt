package com.omaestre.core.domain.model

import com.google.gson.annotations.SerializedName

data class Document(
    @SerializedName("items")
    val items: List<Item>

)
