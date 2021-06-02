package com.omaestre.marvel.domain.model

import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("results")
    val results :List<Heroe>
    )