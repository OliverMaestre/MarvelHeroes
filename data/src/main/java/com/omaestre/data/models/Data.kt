package com.omaestre.data.models

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("results")
    val results: List<Hero>
)