package com.omaestre.data.models

import com.google.gson.annotations.SerializedName

data class Document(
    @SerializedName("items")
    val items: List<Item>

)
